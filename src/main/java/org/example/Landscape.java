package org.example;


import org.example.exceptions.InvalidHillHeightException;
import org.example.exceptions.InvalidLandscapeLengthException;

public class Landscape {

    private final int[] heightsOfPositions;

    public Landscape(int[] heightsOfPositions) {
        int length = heightsOfPositions.length;
        if (length > 32000) {
            throw new InvalidLandscapeLengthException(length);
        }
        this.heightsOfPositions = heightsOfPositions;
    }

    public long calculateWaterAmount() {
        int[] landscape = heightsOfPositions;

        if (landscape.length < 3) {
            for (int height : landscape) {
                validateHillHeight(height);
            }
            return 0;
        }

        long totalAmount = 0;

        int firstHillHeight = landscape[0];
        int secondHillHeight = 0;
        int firstHillPosition = 0;
        int secondHillPosition = 0;

        for (int i = 0; i < landscape.length; i++) {
            int hill = landscape[i];
            validateHillHeight(hill);

            if (firstHillHeight <= hill) { // keeping up to date first hill and finding suitable height and position.
                firstHillHeight = landscape[i];
                firstHillPosition = i;
                continue;
            }

            for (int j = firstHillPosition + 2; j < landscape.length; j++) { // finding suitable second hill to filling water inside pits.
                int tempSecondHill = landscape[j];
                validateHillHeight(tempSecondHill);

                if (secondHillHeight < tempSecondHill) {
                    secondHillHeight = tempSecondHill;
                    secondHillPosition = j;

                    if (secondHillHeight >= firstHillHeight) {
                        i = j - 1;
                        break;
                    }
                }
            }

            for (int k = firstHillPosition + 1; k < secondHillPosition; k++) {
                int hillHeight = landscape[k];

                if (firstHillHeight == secondHillHeight || (firstHillHeight < secondHillHeight && firstHillHeight > hillHeight)) {
                    totalAmount = totalAmount + (firstHillHeight - hillHeight);
                } else if (firstHillHeight > secondHillHeight && secondHillHeight > hillHeight) {
                    totalAmount = totalAmount + (secondHillHeight - hillHeight);
                }
            }

            firstHillPosition = secondHillPosition;
            secondHillHeight = 0;
        }

        return totalAmount;
    }

    private void validateHillHeight(int hill) {
        if (hill < 0 || hill > 32000) {
            throw new InvalidHillHeightException(hill);
        }
    }

}
