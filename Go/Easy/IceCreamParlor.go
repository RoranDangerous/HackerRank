// Ice Cream Parlor
// https://www.hackerrank.com/challenges/icecream-parlor/problem
package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

// Complete the icecreamParlor function below.
func icecreamParlor(m int32, arr []int32) []int32 {
    prev := map[int32]int{};
    for i, a := range arr {
        if prev[m-a] != 0 {
            return []int32{int32(prev[m-a]), int32(i+1)}
        }

        prev[a] = i+1
    }

    return []int32{}
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 1024 * 1024)

    tTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
    checkError(err)
    t := int32(tTemp)

    for tItr := 0; tItr < int(t); tItr++ {
        mTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
        checkError(err)
        m := int32(mTemp)

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

        result := icecreamParlor(m, arr)

        for i, resultItem := range result {
            fmt.Fprintf(writer, "%d", resultItem)

            if i != len(result) - 1 {
                fmt.Fprintf(writer, " ")
            }
        }

        fmt.Fprintf(writer, "\n")
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
