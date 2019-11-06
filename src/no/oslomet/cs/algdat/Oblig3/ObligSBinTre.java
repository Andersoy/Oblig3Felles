package no.oslomet.cs.algdat.Oblig3;

////////////////// ObligSBinTre /////////////////////////////////

import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
  private static final class Node<T>   // en indre nodeklasse
  {
    private T verdi;                   // nodens verdi
    private Node<T> venstre, høyre;    // venstre og høyre barn
    private Node<T> forelder;          // forelder

    // konstruktør
    private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
    {
      this.verdi = verdi;
      venstre = v; høyre = h;
      this.forelder = forelder;
    }

    private Node(T verdi, Node<T> forelder)  // konstruktør
    {
      this(verdi, null, null, forelder);
    }

    @Override
    public String toString(){ return "" + verdi;}

  } // class Node

  private Node<T> rot;                            // peker til rotnoden
  private int antall;                             // antall noder
  private int endringer;                          // antall endringer

  private final Comparator<? super T> comp;       // komparator

  public ObligSBinTre(Comparator<? super T> c)    // konstruktør
  {
    rot = null;
    antall = 0;
    comp = c;
  }
  
  @Override
  public boolean leggInn(T verdi)
  {

    Objects.requireNonNull(verdi);


    Node<T> p = rot;    // p starter i roten
    Node<T> q = null;   // hjelpevariabel
    int cmp = 0;

    while (p != null){       // fortsetter til p er ute av treet

      q = p;                                 // q er forelder til p
      cmp = comp.compare(verdi, p.verdi);    // bruker komparatoren
      p = cmp < 0 ? p.venstre : p.høyre;     // flytter p

    }


    // p er nå null, dvs. ute av treet, q er den siste vi passerte

    p = new Node<>(verdi, q);                   // oppretter en ny node

    if (q == null) {
      rot = p;                  // p blir rotnode
      rot.forelder = null;
    }
    else if (cmp < 0){
      q.venstre = p;         // venstre barn til q
    }
    else{
      q.høyre = p;                        // høyre barn til q
    }


    endringer++;
    antall++;                                // én verdi mer i treet
    return true;                             // vellykket innlegging
  }
  
  @Override
  public boolean inneholder(T verdi)
  {
    if (verdi == null) return false;

    Node<T> p = rot;

    while (p != null)
    {
      int cmp = comp.compare(verdi, p.verdi);
      if (cmp < 0) p = p.venstre;
      else if (cmp > 0) p = p.høyre;
      else return true;
    }

    return false;
  }
  
  @Override
  public boolean fjern(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public int fjernAlle(T verdi)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public int antall()
  {
    return antall;
  }
  
  public int antall(T verdi)
  {
    if (verdi == null) {
      return 0;
    }

    int antallAvVerdi = 0;
    Node node = rot;


    java.util.Deque<Node> fifo_queue = new java.util.ArrayDeque<>();
    fifo_queue.addFirst(node);

    while(fifo_queue.size() > 0) {
      Node current = fifo_queue.removeLast();

      if(current.verdi == verdi){
        antallAvVerdi++;
      }

      if (current.venstre != null) {
        fifo_queue.addFirst(current.venstre);
      }
      if (current.høyre != null) {
        fifo_queue.addFirst(current.høyre);
      }
    }

    return antallAvVerdi;

  }
  
  @Override
  public boolean tom()
  {
    return antall == 0;
  }
  
  @Override
  public void nullstill()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  private static <T> Node<T> nesteInorden(Node<T> p)
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  @Override
  public String toString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String omvendtString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String høyreGren()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String lengstGren()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String[] grener()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String bladnodeverdier()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }
  
  public String postString()
  {
    throw new UnsupportedOperationException("Ikke kodet ennå!");
  }

  @Override
  public Iterator<T> iterator()
  {
    return new BladnodeIterator();
  }

  private class BladnodeIterator implements Iterator<T>
  {
    private Node<T> p, q;
    private boolean removeOK;
    private int iteratorendringer;

    private BladnodeIterator()  {  // konstruktør
      iteratorendringer = endringer;
      removeOK = false;
//        q = null;
      p = rot;

      if(!hasNext()){
        return;
      }

      while(true){

        if(p.venstre != null){

          p = p.venstre;

        }
        else if(p.høyre != null){
          p = p.høyre;
        }
        else{
          break;
        }
      }
    }

    @Override
    public boolean hasNext()
    {
      return p != null;  // Denne skal ikke endres!
    }

    @Override
    public T next() {

      if(iteratorendringer != endringer){
        throw new ConcurrentModificationException("Ikke like endringer");
      }

      if(!hasNext()){
        throw new NoSuchElementException("Ikke flere bladnoder");
      }
      removeOK = true;
      q = p;

      while (p.forelder != null) {

        if (p.forelder.høyre != null && p.forelder.høyre != p) {
          p = p.forelder.høyre;
          break;
        }
        p = p.forelder;
      }

      if( p != rot) {
        while (true) {

          if (p.venstre != null) {
            p = p.venstre;
          } else if (p.høyre != null) {
            p = p.høyre;
          } else {
            break;
          }
        }
      }
      else{
        p = null;
      }

      return q.verdi;
    }

    @Override
    public void remove()
    {
      if (!removeOK){
        throw new IllegalStateException("Ikke lov aa fjerne: ");
      }

      if(endringer != iteratorendringer){
        throw new ConcurrentModificationException("Feil i antall endringer");
      }
      removeOK = false;

      if(q.forelder != null) {

        if (q.forelder.venstre == q) {
          q.forelder.venstre = null;
        }
        else{
          q.forelder.høyre = null;
        }
      }
      q = null;

      antall--;
      endringer++;
      iteratorendringer++;

    }

  } // BladnodeIterator

} // ObligSBinTre
