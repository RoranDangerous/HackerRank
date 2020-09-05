// Weighted Uniform Strings
// https://www.hackerrank.com/challenges/weighted-uniform-string/problem
package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

// Complete the weightedUniformStrings function below.
func weightedUniformStrings(s string, queries []int32) []string {
    alphabet := make([]int32, 26)

    last_char := s[0]
    count := int32(0)
    for i := 0; i < len(s); i++ {
        if last_char == s[i] {
            count += 1
        } else {
            last_char = s[i]
            count = 1
        }

        if alphabet[s[i] - 97] < count {
            alphabet[s[i] - 97] = count
        }
    }

    result := make([]string, len(queries))
    for i, q := range queries {
        for j := int32(1); j <= 26; j++ {
            if q % j == 0 && q / j <= alphabet[j-1] {
                result[i] = "Yes"
                break
            }
            result[i] = "No"
        }
    }

    return result
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 1024 * 1024)

    s := readLine(reader)

    queriesCount, err := strconv.ParseInt(readLine(reader), 10, 64)
    checkError(err)

    var queries []int32

    for i := 0; i < int(queriesCount); i++ {
        queriesItemTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
        checkError(err)
        queriesItem := int32(queriesItemTemp)
        queries = append(queries, queriesItem)
    }

    result := weightedUniformStrings(s, queries)

    for i, resultItem := range result {
        fmt.Fprintf(writer, "%s", resultItem)

        if i != len(result) - 1 {
            fmt.Fprintf(writer, "\n")
        }
    }

    fmt.Fprintf(writer, "\n")

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