def Media(list):
	listResult = 0
	listResult = sum(list)
	listResult /= len(list)

	return listResult

def DesvioPadrao(list):
	listResult = 0
	average = Media(list)
	for val in list:
		listResult += pow((val-average),2)
	listResult /= len(list)

	return listResult



file = open("results.txt")

umPonto = []
doisPontos = []
blender = []

text = file.read().split('\n');

print(len(text))

i=0;
while(i<len(text)-3):
	umPonto.append(text[i])
	doisPontos.append(text[i+1])
	blender.append(text[i+2])
	i+=4


umPonto = [float(x) for x in umPonto]
doisPontos = [float(x) for x in doisPontos]
blender = [float(x) for x in blender]

print(Media(umPonto))
print(DesvioPadrao(umPonto))