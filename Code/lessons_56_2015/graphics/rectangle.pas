

Program Graphika10;
Uses crt, Graph;
var 
i: integer;
gd, gm: Integer;
s: String;
begin
gd := detect;
s := ' ';
Initgraph(gd, gm, s); 
SetBkColor(green); //���� ����
SetLineStyle(3, 0, 3); //��� 0..4, ������� 0..3, ������� 1 ��� 3
Rectangle(100, 100, 400, 500); // ������������� � ���������� �������� ������ ���� � ������� �������
for i := 0 to 11 do
begin
SetFillStyle(i, i); //��� �������� � �� ����
bar (500, 100, 900, 500); // ������������� � ���������� �������� ������ ���� � ������� �������
delay(1000); // ��������
end;
readln;
closegraph
end.



