1.目录结构
--bin
    java可执行文件
--webInspector
    --template
        --Ace                             -- web code editor
        --semantic                        -- semantic UI 前端布局
        --jqueryFileTree                  -- 文件树的js和样式表
        --js                              -- 全局的控制文件main.js
        --css                             -- 层叠样式表文件, main.css中设置了Marker和active path的样式
        --data                            -- data.js, 包含了项目文件的路径和缺陷路径报告的内容。
        --index.html                      -- 索引html
        --startInspect.py                 -- 一个python2脚本，会创建一个本地的httpserver，以处理带参数的get请求（dir、file）。执行后自动打开浏览器
    --test
        用于放置示例的report.xml的文件夹，可以是其他位置
--lib
    一些jar包
--src
    java源码
--README.txt
    本文件

2.main.js代码的说明
initControl()                   --入口函数
initEditor()                    --初始化editor，设置一些属性
initFileTree()                  --初始化文件树，绑定文件路径和点击事件
initFaultsSet()                 --读入缺陷路径，并根据筛选要求进行筛选，添加点击事件
initFaultsFilter()              --定义了checkbox的onchange事件
initPath()                      --将path的内容清空
loadFile()                      --根据路径得到代码，设置代码，去除以前的所有markers和annotations
loadFaultPath()	                --fault表格里元素的点击事件。加载一条缺陷路径，并默认打开第一个节点的文件
removeOldMarkers()              --去除所有之前的markers
addMarker()                     --去掉之前active元素的样式，触发点击事件的元素加上active样式，加载文件，加上annotation和marker, scroll到marker的行

3.java部分
主要功能就是将得到工程文件的路径和report xml的信息，写到data.js中
