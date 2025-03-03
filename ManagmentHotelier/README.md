


```mermaid
---
title: Diagrama Workflow
---
stateDiagram-v2
    [*] --> PornireAplicatie
    PornireAplicatie --> CitireDateDinFisiere
    CitireDateDinFisiere --> AutentificareUtilizator
    AutentificareUtilizator --> RezervareCamera?
    RezervareCamera? --> NumarCamera?
    NumarCamera? --> NumarNopti?
    NumarNopti? --> CameraRezevata
    CameraRezevata --> PrintareChitanta?
    PrintareChitanta? --> Da
    Da --> PrintareChitantaInFisier
    PrintareChitantaInFisier --> InchidereAplicatie
    PrintareChitanta? --> Nu
    Nu --> InchidereAplicatie
    

    AutentificareUtilizator --> InchidereAplicatie
    InchidereAplicatie --> [*]
```

```mermaid
sequenceDiagram
    title Schema bloc
    actor Utilizator
    Utilizator ->> stdin: introducere date
    stdin ->> Java: citire date 
    fisiere.in ->> Java: initizalizare date 
    Java ->> stdout: afisare date
    Java ->> chitanta.txt: creare fisier pt chitanta la cerere
    stdout ->> Utilizator: afisare date
```



Pasi de utilizare:
Utilizatorul porneste aplicatia, sunt afisate toate camerele, hotelurile si preturile.
Se autentifica cu un email si dupa aceea vor aparea informatii despre el, cum ar fi "Numele",
"Bugetul".
Utilizatorul este intrebat daca vrea sa faca o rezervare la acel hotel, dupa la camera dorita,
iar mai apoi cate nopti doreste sa se cazeze.
Se va afisa daca camera a fost rezervata cu succes sau nu si dupa v a fi intrebat daca doreste
chitanta.