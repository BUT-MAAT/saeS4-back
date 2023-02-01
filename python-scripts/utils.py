NUMBER_COL = 75

def writeDelimitation(file, text):
    str = "-- "
    for i in range(NUMBER_COL):
        str += "-"
    str += "\n"
    file.write(str)
    writeSmallDelimitation(file, text)
    file.write(str)

def writeSmallDelimitation(file, text):
    file.write("-- ")
    for i in range((NUMBER_COL - (len(text) + 2)) // 2):
        file.write("-")
    file.write(f" {text} ")
    for i in range((NUMBER_COL - (len(text) + 2)) // 2):
        file.write("-")
    file.write("\n")