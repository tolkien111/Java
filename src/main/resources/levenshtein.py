import sys
from Levenshtein import distance


def is_similar(str1, str2, threshold=3):
    return distance(str1, str2) <= threshold


if __name__ == "__main__":
    user_location = sys.argv[1]
    existing_location = sys.argv[2]
    print(is_similar(user_location, existing_location))
