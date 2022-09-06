# Fundamentals of Genomics & Proteomics
# Lab 03 | 05.09.2022
# Shamim Bin Zahid
# Roll: 43
# written in python3

import math
def partialDigest(distanceList, n):
	maxElement  = max(distanceList)
	pointList = [0] # initializing with zero
	while distanceList:
		maxDistance = max(distanceList)
		pointA, pointB = maxElement - maxDistance, maxDistance
		chosenA, chosenB = True, True
		distanceDifferencesA, distanceDifferencesB = [], []
		# check validity of point A
		for currentPoint in pointList:
			currentDistance = abs(currentPoint - pointA)
			distanceDifferencesA.append(currentDistance)
			if currentDistance not in distanceList:
				chosenA = False
				break
		# check validity of point B
		if not chosenA:
			for currentPoint in pointList:
				currentDistance = abs(currentPoint - pointB)
				distanceDifferencesB.append(currentDistance)
				if currentDistance not in distanceList:
					chosenB = False
					break
		# in case of invalidity of both points
		if not chosenA and not chosenB:
			return None
		# in case of chosing A in the list
		if chosenA:
			pointList.append(pointA) 
			for currentDistance in distanceDifferencesA: 
				distanceList.remove(currentDistance)
		# in case of chosing B in the list
		elif chosenB:
			pointList.append(pointB) 
			for currentDistance in distanceDifferencesB: 
				distanceList.remove(currentDistance)
	return sorted(pointList) # return the points sorted 

if __name__ == "__main__":
	# distanceList = list(map(int,input().strip().split()))
	distanceList = [2, 2, 3, 3, 4, 5, 6, 7, 8, 10]
	n = (1+math.sqrt(1+8*len(distanceList)))/2
	print('Distance List:\t', distanceList)
	pointList = partialDigest(distanceList, int(n))
	if pointList:
		print('Point List:\t', pointList)
	else:
		print('No Solution')