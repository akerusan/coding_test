<div align="center">
  <h1 align="center">With Coding Test</h1>
  
Androidアーキテクチャに関する推奨事項ページから下記項目をりようしました。
  
</div>

## 強く推奨

- 明確に定義されたデータレイヤを使用します
dataパッケージがデーターレイヤになっています。

- 明確に定義された UI レイヤを使用します。
uiパッケージがデーターレイヤになっています。

- データレイヤでは、リポジトリを使用してアプリケーション データを公開する必要があります。
アプリのデータベースとネットワーク接続をviewModelから直接やり取りしないようにリポジトリクラスにInjectしてリポジトリをviewModelで利用してます。

- コルーチンと Flow を使用します。

- 単方向データフロー（UDF）に従います。

- メリットをアプリに適用できる場合は、AAC ViewModel を使用します。

- ViewModel から UI にイベントを送信しないようにします。
viewModelからはステートをUIに渡すように作ってます。

- ViewModel が、Android のライフサイクルに依存しないようにします。
ViewModelにActivity、Fragmen、 ContextやResourcesを依存関係として渡していません。

- コルーチンと Flow を使用します。
KotlinFlow利用し、ViewModelからデータレイヤにviewModelScopeを使ってやり取りになっています。

- 画面レベルで ViewModel を使用します。

- 依存関係挿入を使用します。
Hiltを利用しています

## 推奨

- 単一アクティビティのアプリケーションを使用します。

- Jetpack Compose を使用します。
画面遷移はCompose利用せずNavigationComponentを使いましたがユーザー一覧画面はComposeを利用して作成しました。

- AndroidViewModel を使用しないようにします。
利用しませんでした。

- UI 状態を公開します。
viewModel内のuiState変数で公開しています。

- Hilt を使用します。
使用しています。


