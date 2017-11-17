# MergeTextFiles
Merge all the txt files that in one folder into one text file.

## 简介
修改Main函数开头的变量：

```
String sFilePath = "C:\\w71";
String sOutPutFileName = "\\systems.txt";
```
  
  运行时将会把在C:/w71这个文件夹里的所有txt文件里的内容拷贝到C:/w71/systems.txt文件中去。

## Hints： 
  1.不用事先新建C:/w71/systems.txt
  
  2.systems.txt在第二次运行时会被再拷贝一次
  
  3.运行时会输出文件信息，进度，例如：

\------------------------------------------  
  Absolute path:C:\w71\table_900001-1000000.txt  
  
  File name:table_900001-1000000.txt  
  
  Length for current file:47053890
  
  Lines in total:3580617  
  
\------------------------------------------
  
  36Files are merged
