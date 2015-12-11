# String Calculator Kata
Questo esercizio è stato svolto utilizzando Java, vers. 1.7, come linguaggio di programmazione e Ant, vers.1.9.6, come tool di build del progetto. Per la gestione delle dipendenze è stato utilizzato Ivy, vers 2.3.0.

## Struttura del progetto
La struttura è molto semplice ed è questa:
```
.
├── src
│   ├── main
│   │   └── java
│   └── test
│       └── java
├── build.xml
├── build.properties
├── ivy.xml
├── ivysettings.xml
└── ivy-install.xml
```
dove

* `src/main/java` ospita i sorgenti del progetto;
* `src/test/java` contiene le classi di test;
* `build.xml` è lo script di build per Ant;
* `build.properties` è il file delle proprietà utilizzate da Ant;
* `ivy.xml` è il file utilizzato da Ivy per la gestione delle dipendenze e la definizione delle configurazioni delle librerie. In questo caso l'unica dipendenza è verso JUnit, utilizzato per la compilazione delle classi di test e per l'esecuzione dei test stessi;
* `ivysettings.xml` definisce le impostazioni per Ivy. In questo caso contiene l'indicazione dei resolver per il download delle librerie;
* `ivy-install.xml` è lo script da utilizzare nel caso in cui Ivy non sia presente sulla macchina su cui si vuole compilare il progetto.

In questa struttura trova collocazione la cartella `target`, destinata ad ospitare tutti gli artifact del progetto: classi main e di test compilate, eventuale jar del progetto, report dei test di JUnit, ecc.

## Prerequisiti
Gli unici prerequisiti per la compilazione del progetto sono la presenza di Java, vers. 1.7, e Ant, vers. 1.9.6. Ivy può essere installato eseguendo una-tantum da console lo script ivy-install.xml:
```
ant -f ivy-install.xml
```
nella cartella root del progetto. Con l'esecuzione di questo comando la libreria di Ivy sarà caricata nella cartella `$HOME/.ant/lib` e quindi disponibile come libreria aggiuntiva di Ant

## Compilazione e test
È sufficiente eseguire da console il comando
```
ant
```
nella cartella root del progetto. Lo script effettua, nell'ordine:

* la rimozione di tutti gli artifact già presenti nella cartella `target`;
* la compilazione dei sorgenti del progetto e delle classi di test;
* l'esecuzione del test di JUnit. Per questi viene prodotto un report nella cartella `target/junit-reports/`.
