package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
     * Delivers people based on giving queues (floor map & people) and capacity (lift size)
     *
     * @param queues   int[][] floor map & people distribution
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
     *
     * @param queues   int[][] floor map & people distribution
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
     *
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
     *
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
     *
     * @param floorMap {@link Map}
     */
    private static void liftUnload(Map<Integer, LinkedList<Integer>> floorMap) {
        List<Integer> peopleList = Lift.getLift().stream().filter(value -> value == Lift.getCurrentFloor()).collect(Collectors.toList());
        floorMap.get(Lift.getCurrentFloor()).addAll(peopleList);
        Lift.getLift().removeAll(peopleList);
    }

    /**
     * Calculates next floor to visit based on lift and people rules based on given floor map
     *
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
                    .filter(f -> f > Integer.MIN_VALUE && f > Lift.getCurrentFloor())
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
     *
     * @param floorMap {@link Map}
     * @return boolean value
     */
    private static boolean isPeopleWaiting(Map<Integer, LinkedList<Integer>> floorMap) {
        return isPeopleWaitingToGoUp(floorMap) || isPeopleWaitingToGoDown(floorMap);
    }

    /**
     * Validates if are people waiting to go up based on lift and people rules based on given floor map
     *
     * @param floorMap {@link Map}
     * @return boolean value
     */
    private static boolean isPeopleWaitingToGoUp(Map<Integer, LinkedList<Integer>> floorMap) {
        return getPeopleWaitingForGoUp(floorMap, f -> f != Lift.getCurrentFloor()).stream().count() > 0;
    }

    /**
     * Validates if are people waiting to go down based on lift and people rules based on given floor map
     *
     * @param floorMap {@link Map}
     * @return boolean value
     */
    private static boolean isPeopleWaitingToGoDown(Map<Integer, LinkedList<Integer>> floorMap) {
        return getPeopleWaitingForGoDown(floorMap, f -> f != Lift.getCurrentFloor()).stream().count() > 0;
    }

    /**
     * Validates if are people waiting to go up based on lift and people rules based on given floor map
     *
     * @param floorMap  {@link Map}
     * @param predicate {@link Predicate}
     * @return boolean value
     */
    private static List<Integer> getPeopleWaitingForGoUp(final Map<Integer, LinkedList<Integer>> floorMap, final Predicate<Integer> predicate) {
        return getPeopleWaitingForGoUp(floorMap).stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Validates if are people waiting to go down based on lift and people rules based on given floor map
     *
     * @param floorMap  {@link Map}
     * @param predicate {@link Predicate}
     * @return boolean value
     */
    private static List<Integer> getPeopleWaitingForGoDown(final Map<Integer, LinkedList<Integer>> floorMap, final Predicate<Integer> predicate) {
        return getPeopleWaitingForGoDown(floorMap).stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Gets a list of people thar are waiting to go up based on lift and people rules based on given floor map
     *
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
     *
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
     *
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
     *
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
     *
     * @param queues int[][] queues
     * @return {@link Map} floor map
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
         *
         * @return {@link List}
         */
        public static LinkedList<Integer> getLift() {
            return lift;
        }

        /**
         * Gets the lift floor history / sequence as list of floors
         *
         * @return {@link List}
         */
        public static LinkedList<Integer> getFloorSequence() {
            return floorSequence;
        }

        /**
         * Gets current floor where lift is waiting
         *
         * @return int current flor
         */
        public static int getCurrentFloor() {
            return (floorSequence.size() < 1 ? Integer.MIN_VALUE : floorSequence.getLast());
        }

        /**
         * Gets minimum floor where lift should stop
         *
         * @return int minimum flor
         */
        public static int getMinFloor() {
            return lift.stream().min(Comparator.naturalOrder()).orElse(0);
        }

        /**
         * Gets maximum floor where lift should stop
         *
         * @return int maximum flor
         */
        public static int getMaxFloor() {
            return lift.stream().max(Comparator.naturalOrder()).orElse(0);
        }

        /**
         * Gets direction where lift is going based on latest floors visited
         *
         * @return char direction
         */
        public static char getDirection() {
            return (floorSequence.size() < 2 ? 'U' :
                    (floorSequence.getLast() > floorSequence.get(floorSequence.size() - 2) ? 'U' : 'D'));
        }
    }
}
