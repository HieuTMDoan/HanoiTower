package hanoitower.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Stack;
import java.util.stream.IntStream;

public class Tower implements Serializable {
    @Serial
    private static final long serialVersionUID = -2197309690560975958L;

    public static final String DISK_COUNT_ERROR_MESSAGE = "Negative disk count!";

    public static final String DISK_SIZE_ERROR_MESSAGE = "Negative disk size!";

    private final Stack<Disk> myDiskStack = new Stack<>();

    private final int myTowerID;

    public Tower(final int theDiskCount, final int theTowerID) {
        if (theDiskCount < 0) {
            throw new IllegalArgumentException(DISK_COUNT_ERROR_MESSAGE);
        } else {
            IntStream.iterate(theDiskCount, i -> i > 0, i -> i - 1).mapToObj(Disk::new).forEach(myDiskStack::push);
            myTowerID = theTowerID;
        }
    }

    /*******************************************************************************************************************
     *                                                 GETTER METHODS                                                  *
     *******************************************************************************************************************/
    public int getDiskCount() {
        return myDiskStack.size();
    }

    /*******************************************************************************************************************
     *                                                 SETTER METHODS                                                  *
     *******************************************************************************************************************/
    public Disk popDisk() {
        Disk popDisk = null;

        if (!myDiskStack.empty()) {
            popDisk = myDiskStack.pop();
        }

        return popDisk;
    }

    public void pushDisk(final Disk theDisk) {
        if (this.canPush(theDisk)) {
            myDiskStack.push(theDisk);
        }
    }

    /*******************************************************************************************************************
     *                                                 HELPER METHODS                                                  *
     *******************************************************************************************************************/
    public boolean canPush(final Disk theDisk) {
        return (myDiskStack.size() == 0) || (myDiskStack.peek().getSize() > theDisk.getSize());
    }

    @Override
    public String toString() {
        StringBuilder stringStack = new StringBuilder("[");
        myDiskStack.forEach(disk -> stringStack.append(disk).append(" "));
        if (myDiskStack.size() > 0) {
            stringStack.deleteCharAt(stringStack.length() - 1);
        }
        stringStack.append("]");

        return stringStack.toString();
    }

    @Override
    public boolean equals(Object theObject) {
        return theObject != null
                && this.getClass() == theObject.getClass()
                && this.myTowerID == ((Tower) theObject).myTowerID;
    }

    /*******************************************************************************************************************
     *                                          PACKAGE-PRIVATE CLASS                                                  *
     *******************************************************************************************************************/
    static class Disk implements Serializable{
        @Serial
        private static final long serialVersionUID = 4690013894114118639L;

        private final int mySize;

        private Disk(final int theSize) {
            if (theSize >= 0) {
                mySize = theSize;
            } else {
                throw new IllegalArgumentException(DISK_SIZE_ERROR_MESSAGE);
            }
        }

        private int getSize() {
            return mySize;
        }

        @Override
        public String toString() {
            return "#%d".formatted(this.getSize());
        }
    }
}