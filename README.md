<h1>機械学習によってリンクを分別すること<h1>
<h4>抽出条件： https://support.google.com/adsense/answer/1348688</h4>

このプロジェクトはMavenを使っていますから、プロジェクトのビルドをするために、Mavenのインスタールが必要です。<br>
<br>
JARファイルを作るために、プロジェクトのフォルダでmvn packageというコマンドを実行します。<br>
あとでtargetフォルダでjarファイルが作成されました。<br>
<br>
jarファイルがあるとき、下記のコマンドを実行してください：<br>
java -Dfile.encoding=UTF-8 <b>$param1</b> <b>$param2</b> <b>$param3</b><br>
<br>
<b>param1</b>: 作成されたJARファイル<br>
<b>param2</b>: トレーニングデータのファイル<br>
<b>param3</b>: 設定ファイル</b><br>
設定ファイルの中に下記のフィールドがあります：<br>
・はじめのURL<br>
・抽出の深度<br>
・抽出タイプ  （1:上記の条件に間に合う、1ではない：間に合わない、デフォルト：1）  
デフォルトの設定ファイル： src/main/resources/defaultConfig<br>
url=http://www.gmo-toku.jp<br>
depth=1<br>
output=1<br>

ログを出したい場合、上記のコマンドの終わりに、"2>><b>ログファイルのパス</b>"というパラメターを追加します。<br>