package iostream;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
class Person implements Serializable {
	String name;
	String job;
	//transient로 제외할 수도 있음

	@Override
	public String toString() {
		return name + "," + job;
	}
}

@AllArgsConstructor
@NoArgsConstructor
class Person2 implements Externalizable {
	String name;
	String job;

	@Override
	public String toString() {
		return name + "," + job;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(job);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
		job = in.readUTF();
	}
}


public class SerializationTest {
	public static void main(String[] args) {
		Person2 personLee = new Person2("이순신", "대표이사");
		Person2 personKim = new Person2("김유신", "상무이사");

		try (FileOutputStream fos = new FileOutputStream("serial.txt");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			oos.writeObject(personLee);
			oos.writeObject(personKim);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (FileInputStream fis = new FileInputStream("serial.txt");
			 ObjectInputStream ois = new ObjectInputStream(fis)) {

			Person2 pLee = (Person2)ois.readObject();
			Person2 pKim = (Person2)ois.readObject();

			System.out.println(pLee);
			System.out.println(pKim);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}
}
