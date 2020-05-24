# DateCalculation(日付計算アプリケーション)

## Overview
エンジニアリングスクールRaiseTechのJavaフルコースで作成した任意の日付に対して、計算式を元に日付計算を行うWebアプリケーションです。
・
本課題に関しては詳細な手順書などのドキュメントはスクールより提供されておらず、仕様を元に機能を実装し、独自にカスタマイズしたものです。作成にはJava公式リファレンス、Spring公式ドキュメントなどを元にしています。
・
現在制作途中です。

## Description

アプリケーションとしては画面から計算式の確認が行える。
計算処理はAPI化されているため、外部呼出しも可。
計算を行う為の計算情報のみをDBに保持しており、計算情報は画面から登録することで利用できる。
計算結果は全てUTCで行われ、計算結果はYYYYMMDDで返す。

タイムゾーン対応はしておらず、またフォーマットについても対応していない。

## Requirement

SpringBoot 2.1.13.RELEASE


# Usage


# Author
　Yudai Noda


