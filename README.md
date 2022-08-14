# CardCastle Translator
Translates the exported stuff from CardCastle scanner to a format that deckbox will accept.
Most things accept deckbox exports, so you can just go from there.
## Running
Clone it, install intellij or gradle, then run 
```shell
./gradlew run --args=PATH/TO/EXPORT
```
`out.csv` will be generated in project root then you can import that into [deckbox.org](deckbox.org)