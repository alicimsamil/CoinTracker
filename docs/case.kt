class LinkedList<T> {
    private class Node<T>(val data: T) {
        var next: Node<T>? = null
    }

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun add(data: T) {
        val newNode = Node(data)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
    }

    fun remove(data: T) {
        if (head?.data == data) {
            head = head?.next
            if (head == null) {
                tail = null
            }
            return
        }

        var prevNode: Node<T>? = head
        var currentNode: Node<T>? = head?.next
        while (currentNode != null) {
            if (currentNode.data == data) {
                prevNode?.next = currentNode.next
                if (currentNode == tail) {
                    tail = prevNode
                }
                return
            }
            prevNode = currentNode
            currentNode = currentNode.next
        }
    }

    fun printList() {
        var currentNode: Node<T>? = head
        while (currentNode != null) {
            println(currentNode.data)
            currentNode = currentNode.next
        }
    }
}

fun main() {
    val customers = LinkedList<Int>()
    customers.add(1)
    customers.add(2)
    customers.add(3)
    customers.add(4)
    customers.add(5)
    customers.add(6)
    customers.add(7)
    customers.add(8)

    var currentNode: LinkedList<Int>.Node<Int>? = customers.head
    var totalDistance = 0
    while (currentNode != null) {
        totalDistance += currentNode.data
        currentNode = currentNode.next
    }
}