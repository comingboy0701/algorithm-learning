import copy

nums = "acb"
res = []
tmp = []
def genrate(nums):
    if (len(tmp) == len(nums)):
        res.append(copy.deepcopy(tmp))
        
    for num in nums:
        if num in tmp:
            continue
        else:
            tmp.append(num)
            genrate(nums)
        tmp.pop()


genrate(nums)

res
