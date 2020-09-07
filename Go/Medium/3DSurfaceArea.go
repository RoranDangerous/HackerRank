// 3D Surface Area
// https://www.hackerrank.com/challenges/3d-surface-area/problem
package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
    "strconv"
    "strings"
)

// Complete the surfaceArea function below.
func surfaceArea(A [][]int32) int32 {
    area := int32(0)

    for i, ar := range A {
        for j, ac := range ar {
            sides := sides(A, int32(i), int32(j))
            area += 2
            
            if ac - sides.left > 0 {
                area += ac - sides.left
            }

            if ac - sides.top > 0 {
                area += ac - sides.top
            }

            if ac - sides.bottom > 0 {
                area += ac - sides.bottom
            }

            if ac - sides.right > 0 {
                area += ac - sides.right
            }
        }
    }

    return int32(area)
}

type iSides struct{
    top int32
    bottom int32
    left int32
    right int32
}

func sides(A [][]int32, i, j int32) iSides {
    result := iSides{}
    height := int32(len(A))
    width := int32(len(A[0]))

    if j > 0 {
        result.left = A[i][j-1]
    }

    if j < width - 1 {
        result.right = A[i][j+1]
    }

    if i > 0 {
        result.top = A[i-1][j]
    }

    if i < height - 1 {
        result.bottom = A[i+1][j]
    }

    return result
}

func main() {
    reader := bufio.NewReaderSize(os.Stdin, 1024 * 1024)

    stdout, err := os.Create(os.Getenv("OUTPUT_PATH"))
    checkError(err)

    defer stdout.Close()

    writer := bufio.NewWriterSize(stdout, 1024 * 1024)

    HW := strings.Split(readLine(reader), " ")

    HTemp, err := strconv.ParseInt(HW[0], 10, 64)
    checkError(err)
    H := int32(HTemp)

    WTemp, err := strconv.ParseInt(HW[1], 10, 64)
    checkError(err)
    W := int32(WTemp)

    var A [][]int32
    for i := 0; i < int(H); i++ {
        ARowTemp := strings.Split(readLine(reader), " ")

        var ARow []int32
        for _, ARowItem := range ARowTemp {
            AItemTemp, err := strconv.ParseInt(ARowItem, 10, 64)
            checkError(err)
            AItem := int32(AItemTemp)
            ARow = append(ARow, AItem)
        }

        if len(ARow) != int(int(W)) {
            panic("Bad input")
        }

        A = append(A, ARow)
    }

    result := surfaceArea(A)

    fmt.Fprintf(writer, "%d\n", result)

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
