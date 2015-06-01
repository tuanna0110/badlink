<h1>URL抽出<h1>
<h4>抽出条件： https://support.google.com/adsense/answer/1348688</h4>

このプロジェクトはMavenを使っていますから、プロジェクトのビルドをするために、Mavenのインスタールが必要です。<br>
<br>
JARファイルを作るために、プロジェクトのフォルダでmvn packageというコマンドをご実行ください。<br>
あとでtargetフォルダでjarファイルが作成されました。<br>
<br>
jarファイルがあるとき、下記のコマンドを実行してください：<br>
java -Dfile.encoding=UTF-8 <b>$param1</b> <b>$param2</b> <b>$param3</b><br>
<br>
<b>param1</b>: 作成されたJARファイル<br>
<b>param2</b>: トレーニングデータのファイル<br>
アップロードさてたファイルデータ：　https://app.box.com/s/ktw46oqq44q6cvd3qzwi9jszoz7dk4ds<br>
<b>param3</b>: 設定ファイル</b><br>
設定ファイルの中に下記のフィールドがあります：<br>
・はじめのURL<br>
・抽出の深度<br>
・抽出タイプ  （1:条件に間に合う、1ではない：間に合わない）<br>
デフォルトの設定ファイル： src/main/resources/defaultConfig<br>
url=http://www.gmo-toku.jp<br>
depth=1<br>
output=1<br>

ログを出したい場合、上記のコマンドの終わりに、"2>><b>ログファイルのパス</b>"というパラメターをご追加ください。<br>

※情報共有:<br>
・アルゴリズム：　Transformed Weight-normalized Complement Naive Bayes
・言語：　JAVA。　選択の理由：　JAVAでCrawler, Japanese Tokenizer と機械学習のライブラリがすでにありますから、完全なプログラムを簡単にできます。
・工夫したところ：　
