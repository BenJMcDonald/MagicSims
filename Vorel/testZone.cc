#include "Zone.cc"

#include <assert.h>
using namespace std;

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

};


