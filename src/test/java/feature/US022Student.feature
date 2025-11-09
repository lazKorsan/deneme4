Feature:Kayitli bir kullanici olarak Dashboard panelimde kursları yonetebilecegim  bir sayfanin olmasini istiyorum

  Scenario:TC01  Dashboard sideBar'da Courses menu basligi altında 'New'  linki gorunur ve aktif olmali.


    * Kullanici login sayfasina gider
    * Kullanici mailBox kutusuna mail adresi girer
    * Kullanici passwordBox kutusuna password girer
    * Kullanici submitButon a basar
    * Kullanici dashBoardMenude coursesButona tiklar
    * Kullanici acilan menuden new secenegini tiklar
    * Kullanici coursTypeContainer a tiklar
    * Kullanici videoCourse secenegine tiklar
    * Kullanci basicInformationText uzerine tiklar
    * Kullanici titleBox a course turunu girer
    * Kullanici seoMeataDescription bolumune acmak istedigi ders ile ilgili bilgi girer
    * Kullanici thumbnail sekmesinden a resim yuklemesi yapar
    * Kullanici coverImage sekmesinden resim yuklemsei yapar
    * Kullanici descriptionBox a kurs aciklamasini girer
    * Kullanici teacher newcourses sayfasindaki nextButona basar


  @gogole
  Scenario: TC02 Extra Information icin gerekli form bilgileri doldurulup sonraki sayfaya gecis yapılabilmelidir.

    * Kullanici login sayfasina gider
    * Kullanici mailBox kutusuna mail adresi girer
    * Kullanici passwordBox kutusuna password girer
    * Kullanici submitButon a basar
    * Kullanici dashBoardMenude coursesButona tiklar
    * Kullanici acilan menuden new secenegini tiklar
    * Kullanici coursTypeContainer a tiklar
    * Kullanici videoCourse secenegine tiklar
    * Kullanci basicInformationText uzerine tiklar
    * Kullanici titleBox a course turunu girer
    * Kullanici seoMeataDescription bolumune acmak istedigi ders ile ilgili bilgi girer
    * Kullanici thumbnail sekmesinden a resim yuklemesi yapar
    * Kullanici coverImage sekmesinden resim yuklemsei yapar
    * Kullanici descriptionBox a kurs aciklamasini girer
    * Kullanici sayfanın altında bulunan saveAsDraftButona basar
    * Kullanici svagButon uzerine navigasyon yapar ve tiklar
    * Kullanici editButona tiklar
    * Kullanici teacher newcourses sayfasindaki nextButona basar
    * Kullanici capacityBox kutusuna ogrenci kapasitesi girer
    * Kullanici durationOfNewCoursesBox a ders suresini girer
    * Kullanici supportSwitch butona basarak destek aktif eder
    * Kullanici completionCertifaeced butona basarak sertifika aktif eder
    * Kullanici tagsBox kutusuna akilda kalici bir kelime girer
    * Kullanici catogoryDrapDownMenu den secim yapar




