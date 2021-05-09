public class List {
    public Node head;
    public Node last;
    public int size;
    public int p1;
    public int p2;

    public void insertHead(Peca peca) {
        Node no = new Node();
        no.peca = peca;
        no.prev = null;
        no.next = head;
        if (head != null) {
            head.prev = no;
        }
        head = no;
        if (size == 0) {
            last = head;
        }
        size++;
    }

    public void insertLast(Peca peca) {
        Node no = new Node();
        no.peca = peca;
        no.next = null;
        no.prev = last;
        if(last != null){
            last.next = no;
        }
        last = no;
        if (size == 0) {
            head = last;
        }
        size++;
    }

    public void insertPeca(Peca peca, int i) {
        if (i <= 0) {
            insertHead(peca);
        } else if (i >= size) {
            insertLast(peca);
        } else {
            Node it = head;
            for (int j = 0; j < i; i++) {
                it = it.next;
            }
            Node no = new Node();
            no.peca = peca;
            no.prev = it;
            no.next = it.next;
            it.next = no;
            no.next.prev = no;
            size++;
        }
    }

    public void removePeca(int i) {
        if (i == 0) {
            removeHead();
        } else if (i + 1 == size) {
            removeLast();
        } else {
            Node it = head;
            for (int j = 0; j < i; j++) {
                if(it.next != null){
                    it = it.next;
                }
            }
            if (it.prev != null) {
                it.prev.next = it.next;
            }
            if (it.next != null) {
                it.next.prev = it.prev;
            }
            size--;
        }
    }

    public void removeLast() {
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            head = null;
        }
        size--;
    }

    public void removeHead() {
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            last = null;
        }
        size--;
    }

    public Node returnNode(int i) {
        if (i == 0) {
            return head;
        } else if (i + 1 == size) {
            return last;
        } else {
            Node it = head;
            for (int j = 0; j < i; j++) {
                if(it.next != null){
                    it = it.next;
                }
            }
            return it;
        }
    }
    
    public void imprimirLista(){
        Node it = head;
        int c = 0;
        while(it != null){
            System.out.println(c + "  -  "+it.peca.n1+"  /  "+it.peca.n2);;
            c++;
            it = it.next;
        }
    }
}
