package com.aceprogramming.mac;

/**
 * The World class represents the whole world as viewed from the
 * standpoint of our Missionaries and Cannibals. It is a West bank
 * and East bank of a river that each has a number of Missionaries
 * and Cannibals on it. It also contains a boat that can hold
 * a set number of Missionaries and Cannibals.
 */
public class World {
    private final RiverBank westBank;
    private final RiverBank eastBank;

    private final Boat boat;

    public World(int westBankCannibalCount, int westBankMissionaryCount) {
        this.eastBank = new RiverBank(0, 0, "East Bank",
                false);
        this.westBank = new RiverBank(westBankMissionaryCount, westBankCannibalCount, "West Bank",
                true);
        this.boat = new Boat();
    }

    @Override
    public String toString() {
        return String.format("The state of the world is:\n%s\n%s\n%s\n", westBank,
                eastBank, boat);
    }

    static class RiverBank extends Location{
        private int missionaryCount;
        private int cannibalCount;
        private boolean boat;
        private final String name;

        RiverBank(int missionaryCount, int cannibalCount, String name, boolean hasBoat) {
            this.missionaryCount = missionaryCount;
            this.cannibalCount = cannibalCount;
            this.boat = hasBoat;
            this.name = name;
            if (!isLegalState()) {
                throw new IllegalArgumentException("Cannibals cannot outnumber missionaries or " +
                        "all the missionaries will be eaten.");
            }
        }

        public int getCannibalCount() {
            return this.cannibalCount;
        }

        public int getMissionaryCount() {
            return this.missionaryCount;
        }

        @Override
        public String toString() {
            return String.format("The %s bank of the river has %d missionaries, " +
                            " and %d cannibals. The boat %s on this side of the river.",
                    name, missionaryCount, cannibalCount, boat ? "is" : "is not");
        }
    }

    static class Boat extends Location {
        private int missionaryCount;
        private int cannibalCount;

        public int getMissionaryCount() {
            return missionaryCount;
        }

        public int getCannibalCount() {
            return cannibalCount;
        }

        @Override
        public String toString() {
            return String.format("The boat contains %d missionaries and %d cannibals.", missionaryCount, cannibalCount);
        }
    }

    static abstract class Location {
        abstract int getCannibalCount();
        abstract int getMissionaryCount();

        public boolean isLegalState() {
            return getMissionaryCount() >= getCannibalCount() || getMissionaryCount() == 0;
        }
    }
}
