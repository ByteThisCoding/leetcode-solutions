class Solution {

    /**
     * Depth first search for positions
     * Each position assigned removes possible future positions
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<Integer>> assignedQueenPositions = new ArrayList<>();
        assignedQueenPositions.add(new ArrayList<>());

        boolean[] positionAttacked = new boolean[n * n];
        int numPositionsFree = n * n;

        // find all assignments
        solveNthQueen(
            n,
            n,
            0,
            positionAttacked,
            numPositionsFree,
            assignedQueenPositions
        );

        // turn int into string output for return statement
        List<List<String>> output = new ArrayList<>(n);
        // minus one, because the return includes an empty array
        for (int i=0; i<assignedQueenPositions.size() - 1; i++) {
            List<String> item = new ArrayList<>();
            for (int j=0; j<n; j++) {
                item.add(".".repeat(n));
            }
            output.add(item);
        }
        for (int i=0; i<assignedQueenPositions.size(); i++) {
            List<Integer> assignment = assignedQueenPositions.get(i);
            for (int assignedPosition : assignment) {
                int stringIndex = assignedPosition / n;
                int substringIndex = assignedPosition % n;

                output.get(i).set(
                    stringIndex,
                    ".".repeat(substringIndex) + "Q" + ".".repeat(n - substringIndex - 1)
                );
            }
        }

        return output;
    }

    private void solveNthQueen(
        int width,
        int nRemaining,
        int startPos,
        boolean[] positionAttacked,
        int numPositionsFree,
        List<List<Integer>> assignedQueenPositions
    ) {
        // edge case, too many positions remaining
        if (numPositionsFree > nRemaining*width*4) {
            return;
        }
        // edge case, too many queens
        if (numPositionsFree < nRemaining) {
            return;
        }

        for (int position = startPos; position < width*width - nRemaining + 1; position ++) {
            // don't attack this position if it is already attacked
            if (positionAttacked[position]) {
                continue;
            }

            // get positions this queen attacks
            Set<Integer> attackedPositions = updatePositionsAttackedByQueen(
                width,
                position,
                positionAttacked
            );
            numPositionsFree -= attackedPositions.size();

            // assign this position and recursively search
            assignedQueenPositions
                .get(assignedQueenPositions.size() - 1)
                .add(position);

            if (nRemaining > 1 && numPositionsFree > 0) {
                // skip to the next row
                int nextRowPos = position + width - (position % width);
                solveNthQueen(
                    width,
                    nRemaining - 1,
                    nextRowPos,
                    positionAttacked,
                    numPositionsFree,
                    assignedQueenPositions
                );
            } else if (nRemaining == 1 && numPositionsFree == 0) {
                // copy existing positions and resume backtracking
                List<Integer> newList = new ArrayList<>(width);
                for (int i=0; i<width; i++) {
                    newList.add(
                        assignedQueenPositions
                            .get(assignedQueenPositions.size()-1)
                            .get(i)
                    );
                }
                assignedQueenPositions.add(newList);
            }

            // unassign position and continue
            for (int pos : attackedPositions) {
                positionAttacked[pos] = false;
            }
            numPositionsFree += attackedPositions.size();
            List<Integer> lastList = assignedQueenPositions
                .get(assignedQueenPositions.size() - 1);
            lastList.remove(lastList.size() - 1);
        }
    }

    /**
     * Determine all positions that can be attacked by a queen at a certain position
     * This does not consider positions already attacked
     */
    private Set<Integer> updatePositionsAttackedByQueen(
        int width,
        int queenPosition,
        boolean[] positionAttacked
    ) {
        // allocate to max size
        Set<Integer> attackedPositions = new HashSet<>();

        // left and right
        int rowStart = ((int) queenPosition / width) * width;
        int rowEnd = rowStart + width; //open closed interval

        for (int i=rowStart; i<rowEnd; i++) {
            if (!positionAttacked[i]) {
                attackedPositions.add(i);
                positionAttacked[i] = true;
            }
        }

        // up and down
        int colStart = queenPosition % width;
        int colEnd = colStart + width*(width-1); // open open interval

        for (int i=colStart; i<= colEnd; i+=width) {
            if (!positionAttacked[i]) {
                attackedPositions.add(i);
                positionAttacked[i] = true;
            }
        }

        // northwest to southeast diagonal
        // fan up left
        int diagonalPosition = queenPosition;
        while (diagonalPosition >= 0) {
            if (!positionAttacked[diagonalPosition]) {
                attackedPositions.add(diagonalPosition);
                positionAttacked[diagonalPosition] = true;
            }
            int newPos = diagonalPosition - (width + 1);
            if (newPos / width + 1 != diagonalPosition / width) {
                break;
            }
            diagonalPosition = newPos;
        }

        // fan down right
        diagonalPosition = queenPosition;
        while (diagonalPosition < width*width) {
            if (!positionAttacked[diagonalPosition]) {
                attackedPositions.add(diagonalPosition);
                positionAttacked[diagonalPosition] = true;
            }
            int newPos = diagonalPosition + (width + 1);
            if (newPos / width - 1 != diagonalPosition / width) {
                break;
            }
            diagonalPosition = newPos;
        }

        // southwest to northeast diagonal
        // fan up right
        diagonalPosition = queenPosition;
        while (diagonalPosition >= 0) {
            if (!positionAttacked[diagonalPosition]) {
                attackedPositions.add(diagonalPosition);
                positionAttacked[diagonalPosition] = true;
            }
            int newPos = diagonalPosition - (width - 1);
            // if on the same row, return
            if (newPos / width + 1 != diagonalPosition / width) {
                break;
            }
            diagonalPosition = newPos;
        }

        // fan down left
        diagonalPosition = queenPosition;
        while (diagonalPosition < width*width) {
            if (!positionAttacked[diagonalPosition]) {
                attackedPositions.add(diagonalPosition);
                positionAttacked[diagonalPosition] = true;
            }
            int newPos = diagonalPosition + (width - 1);
            // if on the same row, return
            if (newPos / width - 1 != diagonalPosition / width) {
                break;
            }
            diagonalPosition = newPos;
        }

        return attackedPositions;
    }
}