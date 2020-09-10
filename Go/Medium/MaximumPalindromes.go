// Maximum Palindromes
// https://www.hackerrank.com/challenges/maximum-palindromes/problem
// Last 8 tests are "Terminated due to timeout :(". I gave up looking for possible improvements :(
package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

/*
 * Complete the 'initialize' function below.
 *
 * The function accepts STRING s as parameter.
 */
var S string = ""
var M int64 = 1000000007

func initialize(s string) {
    // This function is called once before all queries.
    S = s
}

/*
 * Complete the 'answerQuery' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER l
 *  2. INTEGER r
 */

func answerQuery(l int32, r int32) int32 {
    // Return the answer for this query modulo 1000000007.
    letters := make([]int32, 26)
    unique := int32(0)
    for i := l - 1; i < r; i++ {
        if letters[S[i] - 97] == int32(0){
            unique++
        }
        letters[S[i] - 97]++
    }

    if unique == int32(1){
        return int32(1)
    }

    middles := int64(0)
    substringLength := int64(0)
    duplicates := int64(1)
    result := int64(1)
    for _, n := range letters {
        if n == 0 {
            continue
        }

        if n == 1 {
            middles++
            continue
        }

        if n % 2 == 1 {
            middles++
        }

        for i := int64(0); int32(i) < n / 2; i++ {
            substringLength++
            result *= substringLength
            result %= M
            duplicates *= i + 1
            duplicates %= M
        }
    }

    duplicates = expBySquaring(duplicates, M - 2)
    result *= duplicates
    result %= M

    if result == 0 {
        return int32(middles % M)
    }

    if middles > 0 {
        result *= middles
        result %= M
    }

    return int32(result)
}

func expBySquaring(x, n int64) int64 {
    if n == 1 {
        return x % M
    }

    if n % 2 == 1 {
        return (x * expBySquaring(x*x % M, (n - 1) / 2)) % M
    }

    return expBySquaring(x*x % M, n / 2)
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 16 * 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 16 * 1024 * 1024)

    s := readLine(reader)

    initialize(s)

    qTemp, err := strconv.ParseInt(strings.TrimSpace(readLine(reader)), 10, 64)
    checkError(err)
    q := int32(qTemp)

    for qItr := 0; qItr < int(q); qItr++ {
        firstMultipleInput := strings.Split(strings.TrimSpace(readLine(reader)), " ")

        lTemp, err := strconv.ParseInt(firstMultipleInput[0], 10, 64)
        checkError(err)
        l := int32(lTemp)

        rTemp, err := strconv.ParseInt(firstMultipleInput[1], 10, 64)
        checkError(err)
        r := int32(rTemp)

        result := answerQuery(l, r)

        fmt.Fprintf(writer, "%d\n", result)
    }

    writer.Flush()
}

func readLine(reader *bufio.Reader) string {
    str, _, err := reader.ReadLine()
    if err == io.EOF {
        return ""
    }

    return strings.TrimRight(string(str), "\r\n")
}

func checkError(err error) {
    if err != nil {
        panic(err)
    }
}
