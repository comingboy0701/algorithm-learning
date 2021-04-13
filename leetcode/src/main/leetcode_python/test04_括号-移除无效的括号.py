def minRemoveToMakeValid(s: str) -> str:
    tmp = []
    valid = set()
    for index, value in enumerate(s):
        if value == "(":
            tmp.append((index, value))
        if value == ")" and tmp and tmp[-1][1] == "(":
            valid.add(tmp.pop(-1)[0])
            valid.add(index)
    res = ""
    for index, value in enumerate(s):
        if value in {"(", ")"} and index in valid:
            res = res + value
        elif value not in {"(", ")"}:
            res = res + value
    return res
