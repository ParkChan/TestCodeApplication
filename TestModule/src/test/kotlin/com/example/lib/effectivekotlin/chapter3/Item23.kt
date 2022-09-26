package com.example.lib.effectivekotlin.chapter3

class Item23 {

    // 타입 파라미터의 섀도잉을 피하라
    interface Tree
    class Birch : Tree
    class Spruce : Tree

    class Forest<T : Tree> {
        fun <T : Tree> addTree(tree: T) {
            //...
        }
    }

    // 코드만 봐서는 둘이 독립적으로 동작한다는 것은 빠르게 알아내기 힘듭니다.
    // 따라서 addTree가 클래스 타입 파라미터인 T를 사용하게 하는것이 좋습니다.

    class Forest2<T : Tree> {
        fun addTree(tree: T) {
            //...
        }
    }
}