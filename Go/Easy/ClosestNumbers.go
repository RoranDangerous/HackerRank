// Closest Numbers
// https://www.hackerrank.com/challenges/closest-numbers/problem
package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
    "sort"
)

// Complete the closestNumbers function below.
func closestNumbers(arr []int32) []int32 {
    sort.Slice(arr, func(i, j int) bool { return arr[i] < arr[j] })
    
    count := 0
    minDiff := int32(10000000)
    for i := 0; i < len(arr) - 1; i++ {
        tmpDiff := arr[i+1] - arr[i]
        if tmpDiff < minDiff {
            count = 1
            minDiff = tmpDiff
        } else if tmpDiff == minDiff {
            count++
        }
    }

    result := make([]int32, 2 * count)

    pos := 0
    for i := 0; i < len(arr) - 1; i++ {
        if arr[i+1] - arr[i] == minDiff {
            result[pos], result[pos+1] = arr[i], arr[i+1]
            pos += 2
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

    nTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
    checkError(err)
    n := int32(nTemp)

    arrTemp := strings.Split(readLine(reader), " ")

    var arr []int32

    for i := 0; i < int(n); i++ {
        arrItemTemp, err := strconv.ParseInt(arrTemp[i], 10, 64)
        checkError(err)
        arrItem := int32(arrItemTemp)
        arr = append(arr, arrItem)
    }

    result := closestNumbers(arr)

    for i, resultItem := range result {
        fmt.Fprintf(writer, "%d", resultItem)

        if i != len(result) - 1 {
            fmt.Fprintf(writer, " ")
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
