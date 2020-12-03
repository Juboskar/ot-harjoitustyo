# Käyttöohje

Sovelluksen käynnistyessä aukeaa kirjautumisnäkymä, jossa on mahdollisuudet kirjautua tai siityä luomaan uutta käyttäjää. 
Kirjautuminen tapahtuu toistaiseksi vain käyttäjätunnuksella.

Onnistuneen kirjautumisen jälkeen pääset lisäämään ja tarkastelemaan päästöjäsi sovelluksen päänäkymässä. Sovellus on suunniteltu siten, että siinä on mahdollista pitää vuoden ajan kirjaa päivittäisistä 
hiilipäästöistään. Päänäkymästä voi siirtyä laskemaan kolmea eri päästökategoriaa, liikennettä, ruokailua tai kulutusta. 
Näitä on siis tarkoitus laskea päivittäin. Unohtuneen päivän voi käydä etsimässä ja täydentämässä jälkikäteen kunkin päästökategorian näkymästä löytyvällä päivämäärävalitsimella.
Myös jo laskettuja päiviä voi palata muokkaamaan.

Päivämäärävalitsimessa on merkitty vihreällä päivät, jolloin päästöt on jo laskettu ja valkoisella laskemattomat. Tulevat päivät ja aloittamisajankohtaa aiemmat päivät näkyvät
punaisella, eikä niitä voi muokata.

HUOM: Kun avaat jonkin päivän jonkin päästökategorian, sen oletusarvot tallentuvat automaattisesti ja päästökategoria merkitään täytetyksi 
(tähän voi sovelluksen jatkokehityksessä tulla vielä muutoksia). Olisi ihanteellista täyttää päästöt siis päästöt sitä mukaa, kun niitä tulee.

(Vinkki lähdekoodin lukijalle: Jos haluat testata käyttäjää, jonka aloittamispäivä on jonain aiempana ajankohtana, voit etsiä ennen koodin kääntämistä luokan 
NewUserSceneController.java ja vaihtaa kohdan LocalDate.now() joksikin muuksi päiväksi. 
Esimerkiksi LocalDate.now().minusDays(100L) koodinpätkällä sovellus siirtää uusien käyttäjien aloituspäivää sadalla aiemmas.)
