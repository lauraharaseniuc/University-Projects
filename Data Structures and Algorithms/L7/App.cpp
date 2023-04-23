#include <iostream>
#include "Matrice.h"
#include "TestExtins.h"
#include "TestScurt.h"
#include <string>

using namespace std;

class Person {
protected: 
	string name;
	int age; 

public: 

	Person() {
		cout << "Constr 1 Person" << endl;
		this->name = "";
		this->age = 0;
	}

	Person(string name , int age): name{name}, age{age}{ 
		cout << "Constr 2 Person" << endl;
	}

	virtual string to_string() {
		return this->name;
	}

	~Person() {
		cout << "Person destructor" << endl;
	}
};

class Student :public Person {
private: 
	int bursa; 

public: 
	Student() {
		cout << "Constr 1 Student" << endl;
		this->name = ""; 
		this->age = 0;
		this->bursa = 0;
	}

	Student(string name, int age, int bursa) {
		cout << "Constr 2 Student" << endl;
		this->name = name;
		this->age = age;
		this->bursa = bursa;
	}

	string to_string() override{
		return this->name + " cu bursa " + std::to_string(this->bursa);
	}

	~Student() {
		cout << "Destructor Student" << endl;
	}
};

int main() {
	//Student s{ "laura", 27 , 100};
	Student* s = new Student("ana", 5, 5);
	Person* p = s;
	cout << p->to_string() << endl;
	//cout << s->to_string();
	/*testAll();
	myTest();
	testAllExtins();

	cout << "End";*/


}
