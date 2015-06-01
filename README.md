<h1>URL抽出<h1>
<h4>抽出条件： https://support.google.com/adsense/answer/1348688</h4>

このプロジェクトはMavenを使っていますから、プロジェクトのビルドをするために、Mavenのインスタールが必要です。<br>
<br>
JARファイルを作るために、プロジェクトのフォルダでmvn packageというコマンドをご実行ください。<br>
あとでtargetフォルダでjarファイルが作成されました。<br>
<br>
jarファイルがあるとき、下記のコマンドをご実行してください：<br>
java -Dfile.encoding=UTF-8 -jar <b>$param1</b> <b>$param2</b> <b>$param3</b><br>
<br>
<b>param1</b>: 作成されたJARファイル (必須）<br>
<b>param2</b>: トレーニングデータのファイル　（必須）<br>
アップロードされたファイルデータ：　https://app.box.com/s/zz0ow8cp874z8por9di67cse8557qjq1<br>
<b>param3</b>: 設定ファイル　（必須ではない）<br>
設定ファイルの中に下記のフィールドがあります：<br>
・クロールしたいサイトのURL<br>
・抽出の深度<br>
・抽出タイプ  （1:条件に間に合う、1ではない：間に合わない）<br>
デフォルトの設定ファイル： src/main/resources/defaultConfig<br>
url=http://www.gmo-toku.jp<br>
depth=1<br>
output=1<br>

ログを出したい場合、上記のコマンドの終わりに、"2>><b>ログファイルのパス</b>"というパラメターをご追加ください。<br>

コマンドを実行する中に、見つかれたURLはすぐで同じなフォルダーでのbadlink.txtファイルに書かれます。<br>
ネットワークの問題があるとき、badlink.txtを出さない場合、ログファイルでの内容を確認して、もう一度実行してお願いします。<br>

URLをたくさん抽出したい場合は、設定ファイルで抽出の深度をお上げください。（時間もたくさんかかります。）<br>

※情報共有:<br>
・アルゴリズム：　Document-Length-Normalized Complement Naive Bayes<br>
・言語：　JAVA。　選択した理由：　JAVAでCrawler, Japanese Tokenizer と機械学習のライブラリが既にありますから、完全なプログラムを簡単にできます。<br>
・工夫したところ：　残念ですが、特にないを思います。品質を上げるためのしたいことが多いですが（さまざまなトレーニングデータを集めて、プログラムを実行するときではなく既に学習実して、より良いアルゴリズムをアプリして）、時間が足りません。<br>
