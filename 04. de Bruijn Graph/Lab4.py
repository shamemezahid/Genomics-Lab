# Assignment 4
# Shamim Bin Zahid
# Roll 43
sequence, k = 'GACTTACGTACT', 3
vertices = [x for x in sequence]
for i in range(k-2, len(sequence)):
    print(vertices[i-1]+vertices[i], end = ' ') if(i+1 == len(sequence)) else print(vertices[i-1]+vertices[i], end = ' > ')