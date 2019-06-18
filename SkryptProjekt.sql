USE Sklep

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Typ_Uzytkownika]') AND type in (N'U'))
BEGIN

CREATE TABLE Typ_Uzytkownika(
id_TypU INT IDENTITY(1,1) PRIMARY KEY,
Nazwa_TypU VARCHAR(20) NOT NULL
)
INSERT INTO Typ_Uzytkownika(Nazwa_TypU) VALUES
('Administrator'),
('Pracownik'),
('Klient')

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Uzytkownik]') AND type in (N'U'))
BEGIN

CREATE TABLE Uzytkownik(
Nazwa_uzytkownika VARCHAR(30) PRIMARY KEY,
Haslo VARCHAR(30) NOT NULL,
Imie VARCHAR(50) NOT NULL,
Nazwisko VARCHAR(50) NOT NULL,
id_TypU INT NOT NULL, 
FOREIGN KEY(id_TypU)
REFERENCES Typ_Uzytkownika(id_TypU) 
)

INSERT INTO Uzytkownik VALUES
('Admin', 'haslo123', 'Ola', 'Kruza', 1),
('mswitaj@onet.pl', 'curly', 'Maja', '�witaj', 2) 

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Adres]') AND type in (N'U'))
BEGIN

CREATE TABLE Adres(
id_Adres INT IDENTITY(1,1) PRIMARY KEY,
Miasto VARCHAR(50) NOT NULL,
Ulica VARCHAR(50) NOT NULL,
Nr_Mieszkania INT NOT NULL,
Kod_Pocztowy INT NOT NULL,
Nazwa_uzytkownika VARCHAR(30),
FOREIGN KEY(Nazwa_uzytkownika)
REFERENCES Uzytkownik(Nazwa_uzytkownika),
)

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Typ_Produktu]') AND type in (N'U'))
BEGIN

CREATE TABLE Typ_Produktu(
id_TypP	INT IDENTITY(1,1) PRIMARY KEY,
Nazwa_TypP VARCHAR(50) NOT NULL
)
INSERT INTO Typ_Produktu(Nazwa_TypP) VALUES
('Od�ywka humektantowa'),
('Od�ywka emolientow'),
('Od�ywka proteinowa'),
('Szampon z SLS'),
('Szampon bez SLS'),
('Olej dla wysokopor�w'),
('Olej dla �redniopor�w'),
('Olej dla niskopor�w'),
('Zio�a farbuj�ce'),
('Zio�a bezbarwne'),
('Krem do stylizacji'),
('�el'),
('Pianka')

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Produkt]') AND type in (N'U'))
BEGIN

CREATE TABLE Produkt(
id_Produkt INT IDENTITY(1,1) PRIMARY KEY,
Nazwa_Produktu VARCHAR(50) NOT NULL,
Cena FLOAT NOT NULL,
Ilosc_Sztuk INT NOT NULL,
id_TypP INT NOT NULL,
Opis_Produktu VARCHAR(500),
FOREIGN KEY(id_TypP)
REFERENCES Typ_Produktu(id_TypP)
)

INSERT INTO Produkt(Nazwa_Produktu, Cena, Ilosc_Sztuk, id_TypP, Opis_Produktu) VALUES
('Gliss Kur Million Gloss', 9.50, 50, 4, 'Szampon do w�os�w matowych, bez po�ysku.'),
('Syoss Color', 15.75, 20, 4, 'Szampon do w�os�w farbowanych lub z pasemkami.'),
('Aussie Volume', 13.25, 24, 4, 'Szampon do w�os�w cienkich i s�abych.'),
('Natura Siberica', 25.50, 37, 4, 'Rokitnikowy szampon do w�os�w normalnych i t�ustych, g�ebokie oczyszczanie i ochrona.'),
('Garnier Fructis Goodby Damage', 7.90, 56, 4, 'Szampon wzmacniaj�cy z ekstraktem z olejku z owoc�w amli.'),
('Schauma Nature Moments', 12.80, 40, 4, 'Szampon do w�os�w s�abych i delikatnych ekstrakt z miodu.'),
('Botanicals Fresh Care', 30.99, 12, 4, 'Szampon piel�gnacyjny do w�os�w suchych.'),
('Elseve Magiczna Moc Glinki', 8.15, 67, 4, 'Szampon do w�os�w normalnych z tendencj� do przet�uszczania.'),
('Anthyllis �agodny Szampon', 11.99, 54, 5, 'Anthyllis Eco Bio Szampon do cz�stego mycia w�os�w 250 ml z wyci�giem z lnu i pokrzywy z ekologicznych upraw. Przepi�kny, unikatowy zapach. Orze�wiaj�cy daj�cy niezwyk�e poczucie ukojenia.'),
('ANWEN Szampon Brzoskwinia i Kolendra', 15.60, 30, 5, 'Formu�a naszego szamponu w piance bazuje na naturalnych detergentach o �agodnym dzia�aniu.'),
('Ecolab Szampon koj�cy', 24.67, 25, 5, 'Szampon koj�cy dla wra�liwej sk�ry g�owy, to produkt dedykowany szczeg�lnie do piel�gnacji bardzo wra�liwej sk�ry g�owy. '),
('JESSICURL Gentle Lather Shampoo', 60.50, 44, 5, 'Szampon pozbawiony substancji zapachowych, stworzony z my�l� o piel�gnacji wra�liwej sk�ry g�owy oraz w�os�w kr�conych.'),
('SATTVA szampon mango', 36.00, 20, 5, '�agodny, naturalnie nawil�aj�cy, �atwo sp�ukuj�cy szampon o zapachu mango. Pomaga zredukowa� frustruj�ce elektryzowanie si� w�os�w. Pozostawia w�osy mi�kkie, zdrowe, jedwabi�cie l�ni�ce i naturalnie pi�kne.'),
('SATTVA szampon shikakai', 36.00, 35, 5, 'Dzi�ki regularnemu stosowaniu szampon chroni w�osy przed utrat� wilgoci i szkodliwym dzia�aniem czynnik�w zewn�trznych. Przywraca w�osom witalno��, spr�ysto�� i blask.'),
('Sylveco Kremowy szampon', 28.00, 12, 5, 'Hypoalergiczny, wyj�tkowo delikatny, przeznaczony do codziennego mycia w�os�w oraz k�pieli niemowl�t i dzieci.'),
('Szampon BIOLAVEN', 13.50, 45, 5, 'Produkt oparty na �agodnej substancji myj�cej (Cocamidopropyl Betaine) oraz �agodnych glukozydach.'),
('Agafia Cedrowy Balsam', 6, 60, 2, 'Balsam do w�os�w na bazie organicznego soku z brzozy wykazuje dzia�anie wzmacniaj�ce i g��boko od�ywiaj�ce os�abione w�osy.'),
('Agafia Ja�owiec Balsam',6, 60, 2, 'Balsam do w�os�w nasyca je i sk�r� g�owy witaminami, wzmacnia cebulki oraz przeciwdzia�a wypadaniu.'),
('ANWEN Emolientowa R�a', 15, 30, 2, 'Bogate w odpowiednie kwasy t�uszczowe oleje oraz mas�o zosta�y optymalnie dopasowane do potrzeb wysokoporowatych w�os�w.'),
('Camille Rose Naturals', 60, 12, 2, 'Receptura maski zosta�a oparta na mleczku migda�owym oraz kompozycji olej�w, takich jak olej migda�owy, z pestek dyni, sezamowy, jojoba, ry�owy, macadamia, oliwa z oliwek oraz rycynowy. '),
('EC Maska laminuj�ca', 45, 20, 2, 'Laminuj�ca maska do w�os�w- b�yskawicznie zmieni w�osy, aktywizuj�c ich naturalny blask i pi�kno na ca�ej d�ugo�ci.'),
('Jane Carter Solution', 64.99, 17, 2, 'Maska bogata we wszystkie sk�adniki i witaminy upi�kszaj�ce w�osy.'),
('EQUILIBRA Naturale ', 19, 56, 1, 'Dzi�ki dzia�aniu naturalnych aktywnych sk�adnik�w doskonale nawil�a, wzmacnia i chroni w�osy. Wysoka zawarto�� Aloesu (20%).'),
('OMIA Maska aloesowa', 25, 70, 1, 'To produkt, kt�ry dog��bnie nawil�y suche, zniszczone i pozbawione obj�to�ci w�osy!'),
('Ecolab Balsam keratyna i acai', 13.75, 40, 3, 'Balsam na mi�kkiej ekologicznej bazie. Ekstrakt jag�d acai w po��czeniu z wyj�tkowymi zio�ami, aktywnie od�ywia, wzmacnia blask w�os�w, kondycjonuje w�osy, u�atwia ich uk�adanie.'),
('KAFE MIMI balsam do w�os�w', 9.99, 50, 3, 'Idealnie chroni w�osy w czasie zabieg�w z u�yciem wysokiej temperatury (prostowanie, uk�adanie, suszenie, przebywanie na s�o�cu).'),
('KAFE MIMI keratynowa maska', 9.99, 45, 3, 'Zosta�a stworzona na bazie keratyny, przywracaj�cej nawet bardzo zniszczonym w�osom �yciow� si��.'),
('Maska Agafii jajeczna', 11.50, 67, 3, 'Sk�ad maski to, proteiny z bia�ek jaj, kt�re intensywnie od�ywiaj� w�osy i sk�r� g�owy, � ytni s��d, niezast�pione �r�d�o mikroelement�w, posiadaj�cy silne dzia�anie regeneracyjne, brzozowy sok stosowany od wiek�w dla wzmocnienia cebulek w�osowych.'),
('Natura Siberica Maska rokitnikowa', 30, 40, 3, 'Rokitnikowa maska dla mocno uszkodzonych w�os�w regeneruje struktur� mocno uszkodzonych w�os�w, chroni je przed negatywnymi skutkami uk�adania na gor�co.'),
('Olvita olej kokosowy', 10, 50, 8, 'Olej kokosowy wyt�aczany jest ze starannie wyselekcjonowanej i przebadanej kopry kokosowej.'),
('Ziaja Mas�o Kakaowe', 21.50, 40, 8, 'Mas�o kakaowe to t�uszcz ro�linny otrzymany z kakaowca.'),
('Olvita olej z czarnuszki', 39.99, 30, 6, 'Olej z czarnuszki, to bogate �r�d�o witaminy E i beta-karotenu. Zawiera sterole, biotyn�, a tak�e przeciwutleniacze.'),
('Olej z pestek winogron', 40, 40, 6, 'Dzi�ki zawarto�ci kwasu linolowego, witaminy E, a tak�e flawonoid�w i resweratrolu, regeneruje barier� hydrolipidow� nask�rka oraz pomaga zwalczy� wolne rodniki.'),
('Olej z nasion kozieradki', 30, 50, 7, 'Kozieradka bogata jest w olejki eteryczne, flawonoidy, cholin�, lecytyn�, witaminy PP, E, D sole mineralne (potas, s�d, mied�, fosfor, �elazo, wap�, cynk).'),
('HESH Amla w proszku',  12, 40, 10, 'Sprawia, �e w�osy zyskuj� zdrowy blask, wzmacnia ich cebulki i nadaje obj�to�ci.'),
('Flos Morszczyn', 5, 100, 10, 'Zawiera jod organiczny i nieorganiczny oraz ca�� gam� witamin (B1, B2, C, D, E, H, K, kwas foliowy) i mikroelementy (�elazo, mangan, kobalt).'),
('SATTVA Henna czysta', 34, 70, 9, 'Nadaje jasnym w�osom przepi�kny rudy kolor, z czerwonymi refleksami, w przypadku w�os�w ciemnych daje pi�kne mahoniowe refleksy.'),
('Camille Rose Naturals Curl', 70, 50, 11, 'Delikatne mleczko przeznaczone do piel�gnacji i stylizacji w�os�w kr�conych.'),
('DevaCurl Texture & Volume', 130, 30, 11, 'Wspania�y krem do stylizacji kr�conych w�os�w. Nadaje pe�nie blasku i obi�to�ci.'),
('Kinky Curly Knot Today', 50, 34, 11, 'Mo�e byc stosowany jako od�ywka bez sp�ukiwania (leave-in) lub jako pomoc przy rozczesywaniu na mokro. Mo�na te� wgniata� niewielk� ilo�� w ociekaj�ce po myciu w�osy jako stylizator.'),
('CANTU twist & lock gel', 35, 20, 12, '�el do stylizacji nadj�cy si� zar�wno na nak�adanie po myciu jak i do reanimacji.'),
('�el bambusowy G-Synergie', 28, 40, 12, 'To wyj�tkowy produkt przeznaczony do piel�gnacji w�os�w, twarzy i cia�a.'),
('Bounce Curl Light Creme gel', 60, 23, 12, 'To lekki �el utrwalaj�cy loki. Dzi�ki swojej �agodnej formule nie obci��a w�os�w oraz doskonale podkre�la ich skr�t.'),
('NIVEA Diamentowy Blask', 14.99, 70, 13, 'Pianka do stylizacji i utrwalania kr�conych w�os�w')

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Koszyk]') AND type in (N'U'))
BEGIN

CREATE TABLE Koszyk(
id_Koszyk INT IDENTITY(1,1) PRIMARY KEY,
id_Produkt INT NOT NULL,
Nazwa_uzytkownika VARCHAR(30) NOT NULL,
Ilosc int NOT NULL,
FOREIGN KEY(id_Produkt)
REFERENCES Produkt(id_Produkt),
FOREIGN KEY(Nazwa_uzytkownika)
REFERENCES Uzytkownik(Nazwa_uzytkownika)
)

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Status_Zamowienia]') AND type in (N'U'))
BEGIN

CREATE TABLE Status_Zamowienia(
id_StatusZ INT IDENTITY(1,1) PRIMARY KEY,
Nazwa_StatusZ VARCHAR(20) NOT NULL,
)
INSERT INTO Status_Zamowienia(Nazwa_StatusZ) VALUES
('Zam�wione'),
('Wys�ane'),
('Dostarczone')

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Zamowienie]') AND type in (N'U'))
BEGIN

CREATE TABLE Zamowienie(
id_Zamowienie INT IDENTITY(1,1) PRIMARY KEY,
Nazwa_uzytkownika VARCHAR(30) NOT NULL,
id_StatusZ INT NOT NULL,
id_Adres INT NOT NULL,
Data_Z DATE NOT NULL,
Koszt FLOAT NOT NULL,
FOREIGN KEY(Nazwa_uzytkownika)
REFERENCES Uzytkownik(Nazwa_uzytkownika),
FOREIGN KEY(id_StatusZ)
REFERENCES Status_Zamowienia(id_StatusZ),
FOREIGN KEY(id_Adres)
REFERENCES Adres(id_Adres)
)

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Produkt_Zamowienie]') AND type in (N'U'))
BEGIN

CREATE TABLE Produkt_Zamowienie(
id_PZ INT IDENTITY(1,1) PRIMARY KEY,
IloscP INT NOT NULL,
id_Produkt INT NOT NULL,
id_Zamowienie INT NOT NULL,
FOREIGN KEY(id_Produkt)
REFERENCES Produkt(id_Produkt),
FOREIGN KEY(id_Zamowienie)
REFERENCES Zamowienie(id_Zamowienie)
)

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Status_Reklamacji]') AND type in (N'U'))
BEGIN

CREATE TABLE Status_Reklamacji(
id_StatusR INT IDENTITY(1,1) PRIMARY KEY,
Nazwa_StatusR VARCHAR(20) NOT NULL,
)
INSERT INTO Status_Reklamacji(Nazwa_StatusR) VALUES
('Wys�ana'),
('Zatwierdzona'),
('Odrzucona')

END

IF  NOT EXISTS (SELECT * FROM sys.objects 
WHERE object_id = OBJECT_ID(N'[dbo].[Reklamacja]') AND type in (N'U'))
BEGIN

CREATE TABLE Reklamacja(
id_Reklamacja INT IDENTITY(1,1) PRIMARY KEY,
id_StatusR INT NOT NULL,
id_Produkt INT NOT NULL,
Data_R DATE NOT NULL,
Nazwa_uzytkownika VARCHAR(30) NOT NULL,
Opis_Problemu VARCHAR(500),
FOREIGN KEY(id_StatusR)
REFERENCES Status_Reklamacji(id_StatusR),
FOREIGN KEY(id_Produkt)
REFERENCES Produkt(id_Produkt),
FOREIGN KEY(Nazwa_uzytkownika)
REFERENCES Uzytkownik(Nazwa_uzytkownika)
)

END

--use Sklep
--drop table Reklamacja, Status_Reklamacji, Produkt_Zamowienie, Zamowienie, Status_Zamowienia
--drop table Koszyk, Produkt, Typ_Produktu, Adres, Uzytkownik, Typ_Uzytkownika
