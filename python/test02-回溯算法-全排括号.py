str_list = []
def genrate(string, left, right):
    if left==0 and right==0:
        str_list.append(string)
    else:
        if left>0:
            genrate(string+"(", left-1, right)
        if left>=0 and left<right:
            genrate(string+")", left, right-1)
    return 


genrate("", 3, 3)


