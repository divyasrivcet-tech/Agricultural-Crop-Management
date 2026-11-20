# Lines of Code (LOC) Analysis Report

## 1. Objective
To calculate the total Lines of Code (LOC) in the Agriculture Crop Management and Yield Analysis System.

## 2. Input
- Project source code files
- File extensions: .java, .html, .css, .js and .py
- Excluded folders: target, .git and node_modules
- Backup files were excluded.

## 3. Methodology
PowerShell was used to count physical lines in the selected source files.

LOC = Total number of physical source-code lines

KLOC = LOC / 1000

## 4. Expected Output
- Java: 14 files, 1094 LOC
- JavaScript: 1 file, 411 LOC
- HTML: 3 files, 251 LOC
- CSS: 2 files, 173 LOC
- Python: 1 file, 33 LOC
- Total: 21 files, 1962 LOC
- KLOC: 1.962

## 5. Actual Output
The PowerShell LOC calculation returned:

| Language | Files | Actual LOC |
|---|---:|---:|
| Java | 14 | 1094 |
| JavaScript | 1 | 411 |
| HTML | 3 | 251 |
| CSS | 2 | 173 |
| Python | 1 | 33 |
| **Total** | **21** | **1962** |

KLOC = 1962 / 1000 = 1.962

## 6. Result
The project contains 1962 physical lines of source code across 21 files. The LOC calculation was completed successfully.

## 7. Conclusion
LOC provides a measurement of software size and can support software effort estimation and project planning.

## 8. Note
This is a physical line count. It may include blank lines and comments; it is not a logical source statement count. LOC is a size measure, not an actual project cost or effort.
