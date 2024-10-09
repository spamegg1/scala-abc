package ocw2016.ps3

class PS3Suite extends munit.FunSuite:
  test("01. getWordScore"):
    val inputs: List[(String, Int)] = List(
      ("", 7),
      ("it", 7),
      ("was", 7),
      ("weed", 6),
      ("scored", 7),
      ("WaYbILl", 7),
      ("Outgnaw", 7),
      ("fork", 7),
      ("FORK", 4)
      // add more test cases here!
    )
    val expected: List[Int] = List(
      0, 2, 54, 176, 351, 735, 539, 209, 308 // add more test cases here!
    )
    assertEquals(inputs map getWordScore, expected)

  test("02. updateHand"):
    val inputs: List[(Hand, String)] = List(
      (
        Map('a' -> 1, 'q' -> 1, 'l' -> 2, 'm' -> 1, 'u' -> 1, 'i' -> 1),
        "quail"
      ),
      (
        Map('e' -> 1, 'v' -> 2, 'n' -> 1, 'i' -> 1, 'l' -> 2),
        "evil"
      ),
      (
        Map('h' -> 1, 'e' -> 1, 'l' -> 2, 'o' -> 1),
        "HELLO"
      )
      // add more test cases here!
    )
    val expected: List[Hand] = List(
      Map('a' -> 0, 'q' -> 0, 'l' -> 1, 'm' -> 1, 'u' -> 0, 'i' -> 0),
      Map('e' -> 0, 'v' -> 1, 'n' -> 1, 'i' -> 0, 'l' -> 1),
      Map('h' -> 0, 'e' -> 0, 'l' -> 0, 'o' -> 0)
      // add more test cases here!
    )
    assertEquals(inputs map updateHand, expected)

  test("03. isValidWord"):
    val wordList = loadWords(WORDLISTFILENAME)
    val inputs: List[(String, Hand)] = List(
      (
        "hello",
        getFreqMap("hello")
      ),
      (
        "Rapture",
        Map('r' -> 2, 'a' -> 3, 'p' -> 2, 'e' -> 1, 't' -> 1, 'u' -> 1)
      ),
      (
        "honey",
        Map('n' -> 1, 'h' -> 1, 'o' -> 1, 'y' -> 1, 'd' -> 1, 'w' -> 1, 'e' -> 2)
      ),
      (
        "honey",
        Map('r' -> 1, 'a' -> 3, 'p' -> 2, 't' -> 1, 'u' -> 2)
      )
      // add more test cases here!
    )
    val expected: List[Boolean] = List(
      true,
      true,
      true,
      false
      // add more test cases here!
    )
    assertEquals(
      inputs.map((word, hand) => isValidWord(word, hand, wordList)),
      expected
    )

// def test_wildcard(word_list):
//     """
//     Unit test for is_valid_word
//     """
//     failure = False

//     # test 1
//     hand =:'a': 1, 'r': 1, 'e': 1, 'j': 2, 'm': 1, '*': 1}
//     word = "e*m"

//     if is_valid_word(word, hand, word_list):
//         print("FAILURE: test_is_valid_word() with wildcards")
//         print("\tExpected False, but got True for word: '" + word + "' and hand:", hand)

//         failure = True

//     # test 2
//     hand =:'n': 1, 'h': 1, '*': 1, 'y': 1, 'd': 1, 'w': 1, 'e': 2}
//     word = "honey"

//     if is_valid_word(word, hand, word_list):
//         print("FAILURE: test_is_valid_word() with wildcards")
//         print("\tExpected False, but got True for word: '" + word + "' and hand:", hand)

//         failure = True

//     # test 3
//     hand =:'n': 1, 'h': 1, '*': 1, 'y': 1, 'd': 1, 'w': 1, 'e': 2}
//     word = "h*ney"

//     if not is_valid_word(word, hand, word_list):
//         print("FAILURE: test_is_valid_word() with wildcards")
//         print("\tExpected True, but got False for word: '" + word + "' and hand:", hand)

//         failure = True

//     # test 4
//     hand =:'c': 1, 'o': 1, '*': 1, 'w': 1, 's': 1, 'z': 1, 'y': 2}
//     word = "c*wz"

//     if is_valid_word(word, hand, word_list):
//         print("FAILURE: test_is_valid_word() with wildcards")
//         print("\tExpected False, but got True for word: '" + word + "' and hand:", hand)

//         failure = True

//     # dictionary of words and scores WITH wildcards
//     words =:("h*ney", 7): 290, ("c*ws", 6): 176, ("wa*ls", 7): 203}
//     for (word, n) in words.keys():
//         score = get_word_score(word, n)
//         if score != words[(word, n)]:
//             print("FAILURE: test_get_word_score() with wildcards")
//             print("\tExpected", words[(word, n)], "points but got '" +
//                   str(score) + "' for word '" + word + "', n=" + str(n))
//             failure = True

//     if not failure:
//         print("SUCCESS: test_wildcard()")
