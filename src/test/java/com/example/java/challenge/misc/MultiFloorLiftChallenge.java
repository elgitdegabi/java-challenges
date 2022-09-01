package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * A multi-floor building has a Lift in it. People are queued on different floors waiting for the Lift.
 * Some people want to go up, some people want to go down. The floor they want to go to is represented by a number.
 * Get all the people to the floors they want to go to while obeying the Lift rules and the People rules.
 * Return a list of all floors that the Lift stopped at in the order visited.
 * The Lift always starts on the ground floor (and people waiting on the ground floor may enter immediately)
 * IMPORTANT:
 * - The Lift never changes direction until there are no more people wanting to get on/off in the direction it is already travelling
 * - When empty the Lift tries to be smart. For example:
 * -- If it was going up then it may continue up to collect the highest floor person wanting to go down
 * -- If it was going down then it may continue down to collect the lowest floor person wanting to go up
 * - The Lift has a maximum capacity of people
 * - When called, the Lift will stop at a floor even if it is full, although unless somebody gets off nobody else can get on
 * - If the lift is empty, and no people are waiting, then it will return to the ground floor
 * - People are in "queues" that represent their order of arrival to wait for the Lift
 * - Only people going the same direction as the Lift may enter it
 * - Entry is according to the "queue" order, but those unable to enter do not block those behind them that can
 * - If a person is unable to enter a full Lift, they will press the UP/DOWN Lift-call button again after it has departed without them
 * <p>
 * For example:
 * Case 1: review parametrized test cases at the end of the class -> review parametrized test cases at the end of the class
 */
@Slf4j
public class MultiFloorLiftChallenge {

    /**
     * MultiFloorLift class
     */
    public static class MultiFloorLift {

        /**
         * Delivers people based on giving queues (floor map & people) and capacity (lift size)
         * @param queues int[][] floor map & people distribution
         * @param capacity int capacity (lift maximum size)
         * @return int[] array of visited floors
         */
        public static int[] theLift(final int[][] queues, final int capacity) {
            Map<Integer, LinkedList<Integer>> floorMap = getFloorMap(queues);

            int currentFloor = 0; // initial load on the ground
            Lift.init(); // initial state

            while (!Lift.getLift().isEmpty() || isPeopleWaiting(floorMap)) {
                Lift.getFloorSequence().add(currentFloor);

                liftUnload(floorMap);
                liftLoad(floorMap, capacity);

                currentFloor = getNextFloor(floorMap);

            }

            // validate latest visited floor
            if (Lift.getFloorSequence().isEmpty() || Lift.getFloorSequence().getLast() != 0) {
                Lift.getFloorSequence().add(0);
            }

            log.info("sequence: {}", Lift.getFloorSequence());

            return Lift.floorSequence.stream().mapToInt(Integer::intValue).toArray();
        }

        /**
         * Delivers people based on giving queues (floor map & people) and capacity (lift size)
         * IMPORTANT: optimized solution
         * @param queues int[][] floor map & people distribution
         * @param capacity int capacity (lift maximum size)
         * @return int[] array of visited floors
         */
        private static int[] theLiftBestSolution(final int[][] queues, final int capacity) {
            //Initialize lift
            ArrayList<Integer> stop = new ArrayList<>();
            boolean direction = true; //True up, False down
            int floor = 0;
            stop.add(floor);
            ArrayList<Integer> lift = new ArrayList<>();

            while (!isEmpty(queues) || !lift.isEmpty()) {
                //If at end turn around
                if (direction && floor == queues.length - 1)
                    direction = false;
                else if (!direction && floor == 0)
                    direction = true;

                //Remove people that want to be at this floor
                if (lift.contains(floor)) {
                    stop.add(floor);
                    lift.removeAll(new ArrayList<>(Arrays.asList(floor)));
                }

                //Add people that want to go in direction (if there's space)
                //Still stops if there are people that want to go in direction
                ArrayList<Integer> newFloor = new ArrayList<>();
                for (int i = 0; i < queues[floor].length; i++) {
                    int target = queues[floor][i];
                    if (stop.get(stop.size() - 1) != floor && (target > floor) == direction)
                        stop.add(floor);
                    if (lift.size() < capacity && (target > floor) == direction)
                        lift.add(target);
                    else
                        newFloor.add(target);
                }
                queues[floor] = newFloor.stream().mapToInt(i -> i).toArray();

                //Go to next floor
                floor = (direction) ? floor + 1 : floor - 1;
            }

            //End at ground floor
            floor = 0;
            if (stop.get(stop.size() - 1) != floor)
                stop.add(floor);

            //Create array from list
            return stop.stream().mapToInt(i -> i).toArray();
        }

        /**
         * Validates if all given queues are empty
         * @param queues int[][] floor map & people distribution
         * @return boolean value when given queues are empty or not
         */
        private static boolean isEmpty(int[][] queues) {
            for (int[] queue : queues)
                if (queue.length != 0)
                    return false;
            return true;
        }

        /**
         * Loads the lift based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @param capacity int capacity (lift maximum size)
         */
        private static void liftLoad(Map<Integer, LinkedList<Integer>> floorMap, final int capacity) {
            if (floorMap.get(Lift.getCurrentFloor()).stream().filter(f -> f != Lift.getCurrentFloor()).count() > 0) {
                List<Integer> peopleFloor = floorMap.get(Lift.getCurrentFloor()).stream().filter(f -> f != Lift.getCurrentFloor()).collect(Collectors.toList());
                int minPeopleGoUp = getPeopleWaitingForGoUp(floorMap).stream().min(Comparator.naturalOrder()).orElse(0);
                int maxPeopleGoDown = getPeopleWaitingForGoDown(floorMap).stream().max(Comparator.naturalOrder()).orElse(0);

                if (!Lift.getLift().isEmpty()) { // lift direction defines the flow
                    if (Lift.getLift().stream().filter(f -> f > Lift.getCurrentFloor()).count() > 0) {
                        peopleFloor = peopleFloor.stream().filter(f -> f > Lift.getCurrentFloor()).collect(Collectors.toList());
                    } else if (Lift.getLift().stream().filter(f -> f < Lift.getCurrentFloor()).count() > 0) {
                        peopleFloor = peopleFloor.stream().filter(f -> f < Lift.getCurrentFloor()).collect(Collectors.toList());
                    } else { // if unload executed previously this case is not possible
                        peopleFloor = Collections.emptyList();
                    }
                } else { // lift is empty -> smart logic
                    if (Lift.getCurrentFloor() == 0) {
                        peopleFloor = peopleFloor.stream().filter(f -> f > Lift.getCurrentFloor()).collect(Collectors.toList());
                    } else if (Lift.getCurrentFloor() == floorMap.size() - 1) {
                        peopleFloor = peopleFloor.stream().filter(f -> f < Lift.getCurrentFloor()).collect(Collectors.toList());
                    } else if (Lift.getDirection() == 'U' && peopleFloor.stream().filter(f -> f > Lift.getCurrentFloor()).count() > 0) {
                        peopleFloor = peopleFloor.stream().filter(f -> f > Lift.getCurrentFloor()).collect(Collectors.toList());
                    } else if (Lift.getDirection() == 'D' && peopleFloor.stream().filter(f -> f < Lift.getCurrentFloor()).count() > 0) {
                        peopleFloor = peopleFloor.stream().filter(f -> f < Lift.getCurrentFloor()).collect(Collectors.toList());
                    } else if (Lift.getCurrentFloor() == minPeopleGoUp) {
                        peopleFloor = peopleFloor.stream().filter(f -> f > Lift.getCurrentFloor()).collect(Collectors.toList());
                    } else if (Lift.getCurrentFloor() == maxPeopleGoDown) {
                        peopleFloor = peopleFloor.stream().filter(f -> f < Lift.getCurrentFloor()).collect(Collectors.toList());
                    } else {
                        peopleFloor = Collections.emptyList();
                    }
                }

                for (int i = 0; i < peopleFloor.size() && Lift.getLift().size() < capacity; i++) {
                    Lift.getLift().add(peopleFloor.get(i));
                    floorMap.get(Lift.getCurrentFloor()).remove(peopleFloor.get(i));
                }
            }
        }

        /**
         * Unloads the lift based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         */
        private static void liftUnload(Map<Integer, LinkedList<Integer>> floorMap) {
            List<Integer> peopleList = Lift.getLift().stream().filter(value -> value == Lift.getCurrentFloor()).collect(Collectors.toList());
            floorMap.get(Lift.getCurrentFloor()).addAll(peopleList);
            Lift.getLift().removeAll(peopleList);
        }

        /**
         * Calculates next floor to visit based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @return next floor to visit
         */
        private static int getNextFloor(final Map<Integer, LinkedList<Integer>> floorMap) {
            int nextLiftFloor = Integer.MIN_VALUE, nextPeopleUpToUp = Integer.MIN_VALUE, nextPeopleUpToDown = Integer.MIN_VALUE, nextPeopleDownToUp = Integer.MIN_VALUE, nextPeopleDownToDown = Integer.MIN_VALUE;
            int nextFloor = 0;

            if (isMovingUp(floorMap)) {
                if (!Lift.getLift().isEmpty()) {
                    nextLiftFloor = Lift.getLift().stream().filter(f -> f > Lift.getCurrentFloor()).min(Comparator.naturalOrder()).orElse(Lift.getMaxFloor());
                }
                if (isPeopleWaitingToGoUp(floorMap)) {
                    nextPeopleUpToUp = getPeopleWaitingForGoUp(floorMap, f -> f > Lift.getCurrentFloor()).stream().min(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
                }
                if (isPeopleWaitingToGoDown(floorMap)) { // change direction
                    nextPeopleUpToDown = getPeopleWaitingForGoDown(floorMap, f -> f > Lift.getCurrentFloor()).stream().max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
                    nextPeopleDownToDown = getPeopleWaitingForGoDown(floorMap, f -> f < Lift.getCurrentFloor()).stream().max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
                }

                nextFloor = List.of(nextLiftFloor, nextPeopleUpToUp).stream()
                        .filter(f -> f > Integer.MIN_VALUE &&  f > Lift.getCurrentFloor())
                        .min(Comparator.naturalOrder()).orElse(nextPeopleUpToDown);

                if (nextFloor < 0) { // change direction flow to DOWN
                    nextFloor = List.of(nextLiftFloor, nextPeopleDownToDown, nextPeopleDownToDown).stream()
                            .filter(f -> f > Integer.MIN_VALUE && f < Lift.getCurrentFloor()).max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
                    if (nextFloor < 0) { // if several direction changes in the middle go to min down floor
                        nextFloor = List.of(nextLiftFloor, nextPeopleDownToDown, nextPeopleDownToDown).stream()
                                .filter(f -> f > Integer.MIN_VALUE && f < Lift.getCurrentFloor()).min(Comparator.naturalOrder()).orElse(0);
                    }
                }
            } else if (isMovingDown(floorMap)) {
                if (!Lift.getLift().isEmpty()) {
                    nextLiftFloor = Lift.getLift().stream().filter(f -> f < Lift.getCurrentFloor()).max(Comparator.naturalOrder()).orElse(Lift.getMinFloor());
                }
                if (isPeopleWaitingToGoDown(floorMap)) {
                    nextPeopleUpToDown = getPeopleWaitingForGoDown(floorMap, f -> f > Lift.getCurrentFloor()).stream().max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
                    nextPeopleDownToDown = getPeopleWaitingForGoDown(floorMap, f -> f < Lift.getCurrentFloor()).stream().max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
                }
                if (isPeopleWaitingToGoUp(floorMap)) { // change direction
                    nextPeopleUpToUp = getPeopleWaitingForGoUp(floorMap, f -> f > Lift.getCurrentFloor()).stream().min(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
                    nextPeopleDownToUp = getPeopleWaitingForGoUp(floorMap, f -> f < Lift.getCurrentFloor()).stream().min(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
                }

                nextFloor = List.of(nextLiftFloor, nextPeopleDownToDown).stream()
                        .filter(f -> f > Integer.MIN_VALUE && f < Lift.getCurrentFloor())
                        .max(Comparator.naturalOrder()).orElse(nextPeopleDownToUp);

                if (nextFloor < 0) { // change direction flow to UP
                    nextFloor = List.of(nextLiftFloor, nextPeopleUpToUp, nextPeopleUpToDown).stream()
                            .filter(f -> f > Integer.MIN_VALUE && f > Lift.getCurrentFloor()).min(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
                    if (nextFloor < 0) { // if several direction changes in the middle go to max up floor
                        nextFloor = List.of(nextLiftFloor, nextPeopleUpToUp, nextPeopleUpToDown).stream()
                                .filter(f -> f > Integer.MIN_VALUE && f > Lift.getCurrentFloor()).max(Comparator.naturalOrder()).orElse(0);
                    }
                }
            }

            return nextFloor;
        }

        /**
         * Validates if are people waiting on upper or lower floors based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @return boolean value
         */
        private static boolean isPeopleWaiting(Map<Integer, LinkedList<Integer>> floorMap) {
            return isPeopleWaitingToGoUp(floorMap) || isPeopleWaitingToGoDown(floorMap);
        }

        /**
         * Validates if are people waiting to go up based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @return boolean value
         */
        private static boolean isPeopleWaitingToGoUp(Map<Integer, LinkedList<Integer>> floorMap) {
            return getPeopleWaitingForGoUp(floorMap, f -> f != Lift.getCurrentFloor()).stream().count() > 0;
        }

        /**
         * Validates if are people waiting to go down based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @return boolean value
         */
        private static boolean isPeopleWaitingToGoDown(Map<Integer, LinkedList<Integer>> floorMap) {
            return getPeopleWaitingForGoDown(floorMap, f -> f != Lift.getCurrentFloor()).stream().count() > 0;
        }

        /**
         * Validates if are people waiting to go up based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @param predicate {@link Predicate}
         * @return boolean value
         */
        private static List<Integer> getPeopleWaitingForGoUp(final Map<Integer, LinkedList<Integer>> floorMap, final Predicate<Integer> predicate) {
            return getPeopleWaitingForGoUp(floorMap).stream().filter(predicate).collect(Collectors.toList());
        }

        /**
         * Validates if are people waiting to go down based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @param predicate {@link Predicate}
         * @return boolean value
         */
        private static List<Integer> getPeopleWaitingForGoDown(final Map<Integer, LinkedList<Integer>> floorMap, final Predicate<Integer> predicate) {
            return getPeopleWaitingForGoDown(floorMap).stream().filter(predicate).collect(Collectors.toList());
        }

        /**
         * Gets a list of people thar are waiting to go up based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @return {@link List<Integer>} of people
         */
        private static List<Integer> getPeopleWaitingForGoUp(final Map<Integer, LinkedList<Integer>> floorMap) {
            return floorMap.keySet().stream()
                    .filter(key -> floorMap.get(key).size() > 0)
                    .filter(key -> floorMap.get(key).stream().filter(target -> target > key).count() > 0)
                    .collect(Collectors.toList());
        }

        /**
         * Gets a list of people thar are waiting to go down based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @return {@link List<Integer>} of people
         */
        private static List<Integer> getPeopleWaitingForGoDown(final Map<Integer, LinkedList<Integer>> floorMap) {
            return floorMap.keySet().stream()
                        .filter(key -> floorMap.get(key).size() > 0)
                        .filter(key -> floorMap.get(key).stream().filter(target -> target < key).count() > 0)
                        .collect(Collectors.toList());
        }

        /**
         * Validates if lift is moving up based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @return boolean value if lift is moving in up direction
         */
        private static boolean isMovingUp(final Map<Integer, LinkedList<Integer>> floorMap) {
            if (Lift.getCurrentFloor() == 0
                    || (Lift.getCurrentFloor() > 0 && Lift.getCurrentFloor() < floorMap.size() - 1 && !Lift.getLift().isEmpty() && Lift.getDirection() == 'U')) {
                return true;
            }
            // be smart
            if (Lift.getLift().isEmpty()) {
                boolean peopleUpToUp = getPeopleWaitingForGoUp(floorMap, f -> f > Lift.getCurrentFloor()).stream().count() > 0;
                boolean peopleUpToDown = getPeopleWaitingForGoDown(floorMap, f -> f > Lift.getCurrentFloor()).stream().count() > 0;

                if (Lift.getDirection() == 'U') {
                    return (peopleUpToUp || peopleUpToDown);
                }

                if (Lift.getDirection() == 'D') {
                    boolean peopleDownToUp = getPeopleWaitingForGoUp(floorMap, f -> f < Lift.getCurrentFloor()).stream().count() > 0;
                    boolean peopleDownToDown = getPeopleWaitingForGoDown(floorMap, f -> f < Lift.getCurrentFloor()).stream().count() > 0;

                    return !peopleDownToUp && !peopleDownToDown && (peopleUpToUp || peopleUpToDown);
                }
            }

            return false;
        }

        /**
         * Validates if lift is moving down based on lift and people rules based on given floor map
         * @param floorMap {@link Map}
         * @return boolean value if lift is moving in down direction
         */
        private static boolean isMovingDown(final Map<Integer, LinkedList<Integer>> floorMap) {
            if (Lift.getCurrentFloor() == floorMap.size() - 1
                    || (Lift.getCurrentFloor() > 0 && Lift.getCurrentFloor() < floorMap.size() - 1 && !Lift.getLift().isEmpty() && Lift.getDirection() == 'D')) {
                return true;
            }
            // be smart
            if (Lift.getLift().isEmpty()) {
                boolean peopleDownToUp = getPeopleWaitingForGoUp(floorMap, f -> f < Lift.getCurrentFloor()).stream().count() > 0;
                boolean peopleDownToDown = getPeopleWaitingForGoDown(floorMap, f -> f < Lift.getCurrentFloor()).stream().count() > 0;

                if (Lift.getDirection() == 'D') {
                    return (peopleDownToUp || peopleDownToDown);
                }

                if (Lift.getDirection() == 'U') {
                    boolean peopleUpToUp = getPeopleWaitingForGoUp(floorMap, f -> f > Lift.getCurrentFloor()).stream().count() > 0;
                    boolean peopleUpToDown = getPeopleWaitingForGoDown(floorMap, f -> f > Lift.getCurrentFloor()).stream().count() > 0;

                    return !peopleUpToUp && !peopleUpToDown && (peopleDownToUp || peopleDownToDown);
                }
            }

            return false;
        }

        /**
         * Gets a {@link Map} of floors and people based on given queues
         * @param queues int[][] queues
         * @return  {@link Map} floor map
         */
        private static Map<Integer, LinkedList<Integer>> getFloorMap(final int[][] queues) {
            Map<Integer, LinkedList<Integer>> floorMap = new HashMap<>();
            for (int i = 0; i < queues.length; i++) {
                LinkedList<Integer> floorList = new LinkedList<>();
                for (int j = 0; j < queues[i].length; j++) {
                    floorList.add(queues[i][j]);
                }
                floorMap.put(i, floorList);
            }

            return floorMap;
        }

        /**
         * Lift class
         */
        private static class Lift {
            private static LinkedList<Integer> lift;
            private static LinkedList<Integer> floorSequence;

            /**
             * Init lift
             */
            public static void init() {
                lift = new LinkedList<>();
                floorSequence = new LinkedList<>();
            }

            /**
             * Gets the lift as list of people
             * @return {@link List}
             */
            public static LinkedList<Integer> getLift() {
                return lift;
            }

            /**
             * Gets the lift floor history / sequence as list of floors
             * @return {@link List}
             */
            public static LinkedList<Integer> getFloorSequence() {
                return floorSequence;
            }

            /**
             * Gets current floor where lift is waiting
             * @return int current flor
             */
            public static int getCurrentFloor() {
                return (floorSequence.size() < 1? Integer.MIN_VALUE: floorSequence.getLast());
            }

            /**
             * Gets minimum floor where lift should stop
             * @return int minimum flor
             */
            public static int getMinFloor() {
                return lift.stream().min(Comparator.naturalOrder()).orElse(0);
            }

            /**
             * Gets maximum floor where lift should stop
             * @return int maximum flor
             */
            public static int getMaxFloor() {
                return lift.stream().max(Comparator.naturalOrder()).orElse(0);
            }

            /**
             * Gets direction where lift is going based on latest floors visited
             * @return char direction
             */
            public static char getDirection() {
                return (floorSequence.size() < 2? 'U':
                        (floorSequence.getLast() > floorSequence.get(floorSequence.size() - 2) ? 'U': 'D'));
            }
        }
    }

    /**
     * Unit test cases class
     */
    public static class MultiFloorLiftTest {
        @ParameterizedTest(name = "scenario: {0}")
        @MethodSource("basicTestCases")
        void parameterizedBasicTestCases(final String scenario, final int[][] queue, final int size, final int[] expected) {
            log.info("capacity: {} - expected: {}", size, expected);
            Assertions.assertArrayEquals(expected, MultiFloorLift.theLift(queue, size));
        }

        /**
         * Generates basic tests cases values
         *
         * @return {@link Stream < Arguments >}
         */
        private static Stream<Arguments> basicTestCases() {
            return Stream.of(
                    arguments("testUp", new int[][]{
                            new int[0], // G
                            new int[0], // 1
                            new int[]{5, 5, 5}, // 2
                            new int[0], // 3
                            new int[0], // 4
                            new int[0], // 5
                            new int[0], // 6
                    }, 5, new int[]{0, 2, 5, 0}),
                    arguments("testDown", new int[][]{
                            new int[0], // G
                            new int[0], // 1
                            new int[]{1, 1}, // 2
                            new int[0], // 3
                            new int[0], // 4
                            new int[0], // 5
                            new int[0], // 6
                    }, 5, new int[]{0, 2, 1, 0}),
                    arguments("testUpAndUp", new int[][]{
                            new int[0], // G
                            new int[]{3}, // 1
                            new int[]{4}, // 2
                            new int[0], // 3
                            new int[]{5}, // 4
                            new int[0], // 5
                            new int[0], // 6
                    }, 5, new int[]{0, 1, 2, 3, 4, 5, 0}),
                    arguments("testDownAndDown", new int[][]{
                            new int[0], // G
                            new int[]{0}, // 1
                            new int[0], // 2
                            new int[0], // 3
                            new int[]{2}, // 4
                            new int[]{3}, // 5
                            new int[0], // 6
                    }, 5, new int[]{0, 5, 4, 3, 2, 1, 0}),
                    arguments("random1", new int[][]{
                            new int[]{1}, // G
                            new int[]{0, 0, 0}, // 1
                            new int[0], // 2
                    }, 3, new int[]{0, 1, 0}),
                    arguments("random2", new int[][]{
                            new int[0], // 0
                            new int[0], // 1
                            new int[]{4,4,4,4}, // 2
                            new int[0], // 3
                            new int[]{2,2,2,2}, // 4
                            new int[0], // 5
                            new int[0] // 6
                    }, 2, new int[]{0, 2, 4, 2, 4, 2, 0}),
                    arguments("testTrickyQueues", new int[][]{
                            new int[0], // G
                            new int[]{0,0,0,6}, // 1
                            new int[0], // 2
                            new int[0], // 3
                            new int[0], // 4
                            new int[]{6,6,0,0,0,6}, // 5
                            new int[0] // 6
                    }, 4, new int[]{0, 1, 5, 6, 5, 1, 0, 1, 0}),
                    arguments("empty", new int[][]{
                            new int[0], // G
                            new int[0], // 1
                            new int[0], // 2
                    }, 3, new int[]{0}),
                    arguments("gabiFullLoad", new int[][]{
                            new int[0], // G
                            new int[0], // 1
                            new int[0], // 2
                            new int[0], // 3
                            new int[]{3}, // 4
                            new int[0], // 5
                            new int[]{1, 2, 3, 3, 1, 4, 3, 3, 3, 1, 3}, // 6
                    }, 3, new int[]{0, 6, 4, 3, 2, 1, 6, 4, 3, 1, 6, 3, 6, 3, 1, 0}),
                    arguments("testUpAndDown", new int[][]{
                            new int[]{3, 3, 3, 3, 3, 3}, // G
                            new int[]{}, // 1
                            new int[]{}, // 2
                            new int[]{}, // 3
                            new int[]{}, // 4
                            new int[]{4, 4, 4, 4, 4, 4}, // 5
                            new int[]{}, // 6
                    }, 5, new int[]{0, 3, 5, 4, 0, 3, 5, 4, 0}),
                     arguments("random3", new int[][]{
                            new int[]{2, 2}, // G
                            new int[]{}, // 1
                            new int[]{0, 1, 0, 0}, // 2
                    }, 1, new int[]{0, 2, 0, 2, 1, 2, 0, 2, 0}),
                     arguments("testLiftFullUp", new int[][]{
                             new int[]{3, 3, 3, 3, 3, 3}, // G
                             new int[]{}, // 1
                             new int[]{}, // 2
                             new int[]{}, // 3
                             new int[]{}, // 4
                             new int[]{}, // 5
                             new int[]{}, // 6
                     }, 5, new int[]{0, 3, 0, 3, 0}),
                     arguments("testHighlander", new int[][]{
                             new int[]{}, // G
                             new int[]{2}, // 1
                             new int[]{3, 3, 3}, // 2
                             new int[]{1}, // 3
                             new int[]{}, // 4
                             new int[]{}, // 5
                             new int[]{}, // 6
                     }, 1, new int[]{0, 1, 2, 3, 1, 2, 3, 2, 3, 0}),
                     arguments("testEnterOnGroundFloor", new int[][]{
                             new int[]{1, 2, 3, 4}, // G
                             new int[]{}, // 1
                             new int[]{}, // 2
                             new int[]{}, // 3
                             new int[]{}, // 4
                             new int[]{}, // 5
                             new int[]{}, // 6
                     }, 5, new int[]{0, 1, 2, 3, 4, 0}),
                      arguments("random4", new int[][]{
                            new int[]{4, 1, 2}, // G
                            new int[]{4}, // 1
                            new int[]{}, // 2
                            new int[]{7, 1}, // 3
                            new int[]{}, // 4
                            new int[]{7, 2, 7}, // 5
                            new int[]{7}, // 6
                            new int[]{0, 2, 1}, // 7
                            new int[]{2}, // 8
                            new int[]{4}, // 9
                    }, 2, new int[]{0, 1, 3, 4, 5, 6, 7, 9, 8, 7, 5, 4, 3, 2, 1, 0, 2, 3, 6, 7, 5, 2, 0, 7, 5, 2, 1, 0}),
                      arguments("random5", new int[][]{
                            new int[]{}, // G
                            new int[]{5, 8}, // 1
                            new int[]{6}, // 2
                            new int[]{8, 5}, // 3
                            new int[]{}, // 4
                            new int[]{6, 3}, // 5
                            new int[]{8, 7, 8, 0}, // 6
                            new int[]{6, 3, 4, 4}, // 7
                            new int[]{}, // 8
                    }, 3, new int[]{0, 1, 2, 3, 5, 6, 7, 8, 7, 6, 5, 4, 3, 0, 3, 5, 6, 8, 7, 5, 4, 3, 0}),
                      arguments("random6", new int[][]{
                            new int[]{}, // G
                            new int[]{5}, // 1
                            new int[]{1, 3, 3}, // 2
                            new int[]{4, 0, 0, 1}, // 3
                            new int[]{2}, // 4
                            new int[]{4, 1, 3}, // 5
                    }, 1, new int[]{0, 1, 2, 3, 5, 4, 3, 2, 1, 2, 3, 4, 5, 3, 1, 2, 3, 5, 3, 0, 3, 0, 3, 1, 0}),
                      arguments("random7", new int[][]{
                            new int[]{3, 6, 3, 6}, // G
                            new int[]{4, 2, 4}, // 1
                            new int[]{3, 1, 7}, // 2
                            new int[]{2, 7, 2, 8}, // 3
                            new int[]{2, 5, 3, 3}, // 4
                            new int[]{1, 4, 1, 3}, // 5
                            new int[]{}, // 6
                            new int[]{8, 5}, // 7
                            new int[]{}, // 8
                    }, 2, new int[]{0, 1, 2, 3, 4, 6, 7, 8, 7, 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 6, 8, 5, 4, 3, 2, 1, 2, 3, 4, 5, 4, 3, 2, 1, 2, 4, 7, 0}),
                      arguments("random8", new int[][]{
                            new int[]{}, // G
                            new int[]{0, 13}, // 1
                            new int[]{0, 8, 11}, // 2
                            new int[]{1, 4, 2}, // 3
                            new int[]{0}, // 4
                            new int[]{7, 7, 10}, // 5
                            new int[]{9}, // 6
                            new int[]{10, 9, 9}, // 7
                            new int[]{}, // 8
                            new int[]{}, // 9
                            new int[]{}, // 10
                            new int[]{1, 4}, // 11
                            new int[]{4}, // 12
                            new int[]{4, 2, 9, 12}, // 13
                            new int[]{5, 0, 6}, // 14
                    }, 1, new int[]{0, 1, 2, 3, 5, 6, 7, 13, 14, 13, 12, 11, 5, 4, 3, 2, 1, 0, 2, 3, 5, 6, 7, 8, 14, 13, 12, 11, 3, 2, 1, 0, 2, 3, 5, 6, 7, 11, 14, 13, 12, 11, 6, 3, 2, 1, 0, 3, 4, 5, 6, 7, 10, 13, 12, 11, 4, 3, 2, 0, 5, 6, 7, 9, 13, 12, 11, 2, 5, 6, 7, 10, 13, 12, 11, 9, 6, 7, 9, 13, 12, 11, 4, 7, 9, 11, 1, 11, 4, 0})
            );
        }
    }
}
