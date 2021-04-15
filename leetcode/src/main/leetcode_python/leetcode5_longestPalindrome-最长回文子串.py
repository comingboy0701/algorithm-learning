class Solution:
    def longestPalindrome(self, s: str) -> str:
        res = ""
        for i in range(0, len(s)):
            res_1 = self.palindrome(s, i, i+1)
            res_2 = self.palindrome(s, i, i)
            if len(res)<len(res_1):
                res = res_1
            if len(res)<len(res_2):
                res = res_2
        return res


    def palindrome(self, s, left, right):
        while(left>=0 and right<len(s) and s[left]==s[right]):
            left = left-1
            right = right+1
        return s[left+1:right]


res = Solution().longestPalindrome("a")
print(res)
