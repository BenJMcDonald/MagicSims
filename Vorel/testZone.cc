#include <string>
#include <assert.h>
using namespace std;

#include "convertInt.cc"
#include "Zone.cc"

int main(){
    assert(0==0);

    Zone* n = new Zone();

    assert(n->contains("Forest")==false);
    n->add("Forest");
    assert(n->contains("Forest")==true);
    assert(n->contains("Island")==false);
    assert(n->drop("Island")==false);
    assert(n->getSize() == 1);
    n->add("Forest");
    n->add("Island");
    n->add("Mountain");
    n->print();
    assert(n->drop("Forest")==true);
    assert(n->drop("Forest")==true);
    assert(n->drop("Forest")==false);
    assert(n->drop("Mountain")==true);
    assert(n->drop("Island")==true);
    assert(n->getSize()==0);
    delete n;

    Zone* p = new Zone();
    p->add("Forest");
    p->add("Island");
    assert(p->drop("Forest") == true);
    assert(p->drop("Forest") == false);
    assert(p->drop("Island") == true);
    assert(p->drop("Island") == false);
    delete p;

    p = new Zone();
    p->add("Forest");
    p->add("Island");
    p->add("Mountain");
    assert(p->drop("Forest") == true);
    assert(p->drop("Forest") == false);
    assert(p->drop("Island") == true);
    assert(p->drop("Island") == false);
    assert(p->drop("Mountain") == true);
    assert(p->drop("Mountain") == false);
    delete p;
    
    p = new Zone();
    p->add("Forest");
    p->add("Island");
    p->add("Mountain");
    assert(p->drop("Island") == true);
    assert(p->drop("Island") == false);
    assert(p->drop("Forest") == true);
    assert(p->drop("Forest") == false);
    assert(p->drop("Mountain") == true);
    assert(p->drop("Mountain") == false);
    delete p;
    
    p = new Zone();
    p->add("Forest");
    p->add("Island");
    p->add("Mountain");
    assert(p->drop("Mountain") == true);
    assert(p->drop("Mountain") == false);
    assert(p->drop("Island") == true);
    assert(p->drop("Island") == false);
    assert(p->drop("Forest") == true);
    assert(p->drop("Forest") == false);
    delete p;

};


