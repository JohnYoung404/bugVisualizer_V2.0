1.Ŀ¼�ṹ
��Ŀ¼��һ��java���̣�
��Ŀ¼�µ�home�ļ�����һ��ʾ��,Ϊ����ʾxml�е�·��;
data�ļ����µ�template�ļ�������ҳ��ģ���ļ���,���ڲ�ͬ���̵���ҳ��ֻ�ı���template/data/data.js��
data�ļ����µ�test�ļ����°�����xml�ļ�

2.template�ļ��нṹ:
Ace 			-- web code editor
semantic 		-- semantic UI ǰ�˲���
jqueryFileTree 	-- �ļ�����ʵ��
js 				-- javascript�ļ�, ������Ҫ�Ŀ����ļ�main.js
css				-- �����ʽ���ļ�, main.css��������Marker��active path����ʽ
data			-- data.js����ɱ������

3.main.js�����˵��
initControl()			--��ں���
initEditor()			--��ʼ��editor������һЩ����
initFileTree()			--��ʼ���ļ��������ļ�·���͵���¼�
initFaultsSet()			--����ȱ��·����������ɸѡҪ�����ɸѡ����ӵ���¼�
initFaultsFilter()		--������checkbox��onchange�¼�
initPath()				--��path���������
loadFile()				--����·���õ����룬���ô��룬ȥ����ǰ������markers��annotations
loadFaultPath()			--fault�����Ԫ�صĵ���¼�������һ��ȱ��·������Ĭ�ϴ򿪵�һ���ڵ���ļ�
removeOldMarkers()		--ȥ������֮ǰ��markers
addMarker()				--ȥ��֮ǰactiveԪ�ص���ʽ����������¼���Ԫ�ؼ���active��ʽ�������ļ�������annotation��marker, scroll��marker����

4.java����
��Ҫ�����ǵõ�report xml����Ϣ��д��data.js��

5.startInspect.py˵��
��python 2�Ľű���ִ������ű������һ���򵥵�HTTPserver,����Ӧfile, dir������������get���󣬲������ļ����ݻ���Ŀ¼��Ϣ��
�ű����Զ���index.html������http://localhost:8080��֮ͨ��