#include "LList.cc"
#include "Card.cc"
#include "Hand.cc"

int main(){
    cout<<"Constructing hand\n";
    Card* m = new Card(land);
    Card* l = new Card(burn);
    Hand* h = new Hand();
    h->print();
    cout<<"Inserting cards\n";
    h->add(m);
    h->add(l);
    h->add(l);
    h->add(m);
    h->print();
    cout<<'\n';
    cout<<"Contains mountain- "<<h->contains(m)<<'\n';
    cout<<"Contains bolt- "<<h->contains(l)<<'\n';
    cout<<"Deleting cards\n";
    for(int i = 0; i<3; i++){
	cout<<"Delete mountain- "<<h->drop(m)<<'\n';
	h->print();
    }
    cout<<"Contains mountain- "<<h->contains(m)<<'\n';
    cout<<"Contains bolt- "<<h->contains(l)<<'\n';
    for(int i = 0; i<3; i++){
	cout<<"Delete bolt- "<<h->drop(l)<<'\n';
	h->print();
    }
    cout<<"Contains mountain- "<<h->contains(m)<<'\n';
    cout<<"Contains bolt- "<<h->contains(l)<<'\n';
    
}
