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
('mswitaj@onet.pl', 'curly', 'Maja', 'Œwitaj', 2) 

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
('Od¿ywka humektantowa'),
('Od¿ywka emolientow'),
('Od¿ywka proteinowa'),
('Szampon z SLS'),
('Szampon bez SLS'),
('Olej dla wysokoporów'),
('Olej dla œrednioporów'),
('Olej dla niskoporów'),
('Zio³a farbuj¹ce'),
('Zio³a bezbarwne'),
('Krem do stylizacji'),
('¯el'),
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
('Gliss Kur Million Gloss', 9.50, 50, 4, 'Szampon do w³osów matowych, bez po³ysku.'),
('Syoss Color', 15.75, 20, 4, 'Szampon do w³osów farbowanych lub z pasemkami.'),
('Aussie Volume', 13.25, 24, 4, 'Szampon do w³osów cienkich i s³abych.'),
('Natura Siberica', 25.50, 37, 4, 'Rokitnikowy szampon do w³osów normalnych i t³ustych, g³ebokie oczyszczanie i ochrona.'),
('Garnier Fructis Goodby Damage', 7.90, 56, 4, 'Szampon wzmacniaj¹cy z ekstraktem z olejku z owoców amli.'),
('Schauma Nature Moments', 12.80, 40, 4, 'Szampon do w³osów s³abych i delikatnych ekstrakt z miodu.'),
('Botanicals Fresh Care', 30.99, 12, 4, 'Szampon pielêgnacyjny do w³osów suchych.'),
('Elseve Magiczna Moc Glinki', 8.15, 67, 4, 'Szampon do w³osów normalnych z tendencj¹ do przet³uszczania.'),
('Anthyllis £agodny Szampon', 11.99, 54, 5, 'Anthyllis Eco Bio Szampon do czêstego mycia w³osów 250 ml z wyci¹giem z lnu i pokrzywy z ekologicznych upraw. Przepiêkny, unikatowy zapach. OrzeŸwiaj¹cy daj¹cy niezwyk³e poczucie ukojenia.'),
('ANWEN Szampon Brzoskwinia i Kolendra', 15.60, 30, 5, 'Formu³a naszego szamponu w piance bazuje na naturalnych detergentach o ³agodnym dzia³aniu.'),
('Ecolab Szampon koj¹cy', 24.67, 25, 5, 'Szampon koj¹cy dla wra¿liwej skóry g³owy, to produkt dedykowany szczególnie do pielêgnacji bardzo wra¿liwej skóry g³owy. '),
('JESSICURL Gentle Lather Shampoo', 60.50, 44, 5, 'Szampon pozbawiony substancji zapachowych, stworzony z myœl¹ o pielêgnacji wra¿liwej skóry g³owy oraz w³osów krêconych.'),
('SATTVA szampon mango', 36.00, 20, 5, '£agodny, naturalnie nawil¿aj¹cy, ³atwo sp³ukuj¹cy szampon o zapachu mango. Pomaga zredukowaæ frustruj¹ce elektryzowanie siê w³osów. Pozostawia w³osy miêkkie, zdrowe, jedwabiœcie lœni¹ce i naturalnie piêkne.'),
('SATTVA szampon shikakai', 36.00, 35, 5, 'Dziêki regularnemu stosowaniu szampon chroni w³osy przed utrat¹ wilgoci i szkodliwym dzia³aniem czynników zewnêtrznych. Przywraca w³osom witalnoœæ, sprê¿ystoœæ i blask.'),
('Sylveco Kremowy szampon', 28.00, 12, 5, 'Hypoalergiczny, wyj¹tkowo delikatny, przeznaczony do codziennego mycia w³osów oraz k¹pieli niemowl¹t i dzieci.'),
('Szampon BIOLAVEN', 13.50, 45, 5, 'Produkt oparty na ³agodnej substancji myj¹cej (Cocamidopropyl Betaine) oraz ³agodnych glukozydach.'),
('Agafia Cedrowy Balsam', 6, 60, 2, 'Balsam do w³osów na bazie organicznego soku z brzozy wykazuje dzia³anie wzmacniaj¹ce i g³êboko od¿ywiaj¹ce os³abione w³osy.'),
('Agafia Ja³owiec Balsam',6, 60, 2, 'Balsam do w³osów nasyca je i skórê g³owy witaminami, wzmacnia cebulki oraz przeciwdzia³a wypadaniu.'),
('ANWEN Emolientowa Ró¿a', 15, 30, 2, 'Bogate w odpowiednie kwasy t³uszczowe oleje oraz mas³o zosta³y optymalnie dopasowane do potrzeb wysokoporowatych w³osów.'),
('Camille Rose Naturals', 60, 12, 2, 'Receptura maski zosta³a oparta na mleczku migda³owym oraz kompozycji olejów, takich jak olej migda³owy, z pestek dyni, sezamowy, jojoba, ry¿owy, macadamia, oliwa z oliwek oraz rycynowy. '),
('EC Maska laminuj¹ca', 45, 20, 2, 'Laminuj¹ca maska do w³osów- b³yskawicznie zmieni w³osy, aktywizuj¹c ich naturalny blask i piêkno na ca³ej d³ugoœci.'),
('Jane Carter Solution', 64.99, 17, 2, 'Maska bogata we wszystkie sk³adniki i witaminy upiêkszaj¹ce w³osy.'),
('EQUILIBRA Naturale ', 19, 56, 1, 'Dziêki dzia³aniu naturalnych aktywnych sk³adników doskonale nawil¿a, wzmacnia i chroni w³osy. Wysoka zawartoœæ Aloesu (20%).'),
('OMIA Maska aloesowa', 25, 70, 1, 'To produkt, który dog³êbnie nawil¿y suche, zniszczone i pozbawione objêtoœci w³osy!'),
('Ecolab Balsam keratyna i acai', 13.75, 40, 3, 'Balsam na miêkkiej ekologicznej bazie. Ekstrakt jagód acai w po³¹czeniu z wyj¹tkowymi zio³ami, aktywnie od¿ywia, wzmacnia blask w³osów, kondycjonuje w³osy, u³atwia ich uk³adanie.'),
('KAFE MIMI balsam do w³osów', 9.99, 50, 3, 'Idealnie chroni w³osy w czasie zabiegów z u¿yciem wysokiej temperatury (prostowanie, uk³adanie, suszenie, przebywanie na s³oñcu).'),
('KAFE MIMI keratynowa maska', 9.99, 45, 3, 'Zosta³a stworzona na bazie keratyny, przywracaj¹cej nawet bardzo zniszczonym w³osom ¿yciow¹ si³ê.'),
('Maska Agafii jajeczna', 11.50, 67, 3, 'Sk³ad maski to, proteiny z bia³ek jaj, które intensywnie od¿ywiaj¹ w³osy i skórê g³owy, ¿ ytni s³ód, niezast¹pione Ÿród³o mikroelementów, posiadaj¹cy silne dzia³anie regeneracyjne, brzozowy sok stosowany od wieków dla wzmocnienia cebulek w³osowych.'),
('Natura Siberica Maska rokitnikowa', 30, 40, 3, 'Rokitnikowa maska dla mocno uszkodzonych w³osów regeneruje strukturê mocno uszkodzonych w³osów, chroni je przed negatywnymi skutkami uk³adania na gor¹co.'),
('Olvita olej kokosowy', 10, 50, 8, 'Olej kokosowy wyt³aczany jest ze starannie wyselekcjonowanej i przebadanej kopry kokosowej.'),
('Ziaja Mas³o Kakaowe', 21.50, 40, 8, 'Mas³o kakaowe to t³uszcz roœlinny otrzymany z kakaowca.'),
('Olvita olej z czarnuszki', 39.99, 30, 6, 'Olej z czarnuszki, to bogate Ÿród³o witaminy E i beta-karotenu. Zawiera sterole, biotynê, a tak¿e przeciwutleniacze.'),
('Olej z pestek winogron', 40, 40, 6, 'Dziêki zawartoœci kwasu linolowego, witaminy E, a tak¿e flawonoidów i resweratrolu, regeneruje barierê hydrolipidow¹ naskórka oraz pomaga zwalczyæ wolne rodniki.'),
('Olej z nasion kozieradki', 30, 50, 7, 'Kozieradka bogata jest w olejki eteryczne, flawonoidy, cholinê, lecytynê, witaminy PP, E, D sole mineralne (potas, sód, miedŸ, fosfor, ¿elazo, wapñ, cynk).'),
('HESH Amla w proszku',  12, 40, 10, 'Sprawia, ¿e w³osy zyskuj¹ zdrowy blask, wzmacnia ich cebulki i nadaje objêtoœci.'),
('Flos Morszczyn', 5, 100, 10, 'Zawiera jod organiczny i nieorganiczny oraz ca³¹ gamê witamin (B1, B2, C, D, E, H, K, kwas foliowy) i mikroelementy (¿elazo, mangan, kobalt).'),
('SATTVA Henna czysta', 34, 70, 9, 'Nadaje jasnym w³osom przepiêkny rudy kolor, z czerwonymi refleksami, w przypadku w³osów ciemnych daje piêkne mahoniowe refleksy.'),
('Camille Rose Naturals Curl', 70, 50, 11, 'Delikatne mleczko przeznaczone do pielêgnacji i stylizacji w³osów krêconych.'),
('DevaCurl Texture & Volume', 130, 30, 11, 'Wspania³y krem do stylizacji krêconych w³osów. Nadaje pe³nie blasku i obiêtoœci.'),
('Kinky Curly Knot Today', 50, 34, 11, 'Mo¿e byc stosowany jako od¿ywka bez sp³ukiwania (leave-in) lub jako pomoc przy rozczesywaniu na mokro. Mo¿na te¿ wgniataæ niewielk¹ iloœæ w ociekaj¹ce po myciu w³osy jako stylizator.'),
('CANTU twist & lock gel', 35, 20, 12, '¯el do stylizacji nadj¹cy siê zarówno na nak³adanie po myciu jak i do reanimacji.'),
('¯el bambusowy G-Synergie', 28, 40, 12, 'To wyj¹tkowy produkt przeznaczony do pielêgnacji w³osów, twarzy i cia³a.'),
('Bounce Curl Light Creme gel', 60, 23, 12, 'To lekki ¿el utrwalaj¹cy loki. Dziêki swojej ³agodnej formule nie obci¹¿a w³osów oraz doskonale podkreœla ich skrêt.'),
('NIVEA Diamentowy Blask', 14.99, 70, 13, 'Pianka do stylizacji i utrwalania krêconych w³osów')

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
('Zamówione'),
('Wys³ane'),
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
('Wys³ana'),
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
