PROGRAM  Domiki;
      uses Graph;
      var  grDriver: Integer;
           grMode  : Integer;
           i,j     : Integer;
  { ------------------------------ }
   PROCEDURE  Domik (x,y: Integer);
   { ������ �����, � �������� ����� ������ ���� }
   { ����� ���������� (x,y)                     }
      const dx=60;     { ������ ������ }
            dy=40;     { ������ ������ }
            dx2=dx DIV 2;
            dy2=dy DIV 2;
            wx=16;     { ������ ���� }
            wy=22;     { ������ ���� }
            wx2=wx DIV 2;
            wy2=wy DIV 2;
   BEGIN
      Rectangle (x,y,x+dx,y-dy); MoveTo (x,y-dy);
      Linerel (dx2,-dx2);  { ����� ���� ����� }
      Linerel (dx2,dx2);   { ����� ���� ����� }
      Rectangle (x+dx2-wx2,y-dy2-wy2,x+dx2+wx2,y-dy2+wy2);  { ���� }
      MoveTo (x+dx2,y-dy2);       { ����� ������ (� ����)          }
      LineRel (0,wy2);            { ������������ ����� ���� ����   }
      MoveTo (x+dx2-wx2,y-dy2);   { ����� ����� ����� ���� ����    }
      LineRel (wx,0);             { �������������� ����� ���� ���� }
      SetFillStyle (SolidFill,Red);
      FloodFill (x+1,y-1,White);
      SetFillStyle (SolidFill,Blue);
      FloodFill (x+dx2,y-dy-1,White)
   END;
  { --- }
   BEGIN
      grDriver:=VGA; grMode:=VGAHi;
      InitGraph (grDriver,grMode,'..\bgi');
      If  GraphResult=grOk
         then  begin
                  For i:=1 to 6  do
                     For j:=1 to 5  do
                        Domik (i*80,j*80);
                  ReadLn
               end
   END.