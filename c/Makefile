.PHONY: test, clean

matmpy: matmpy.c
	gcc -g matmpy.c -o matmpy

result.csv: ./matmpy
	./matmpy /opt/data/mat_a.csv /opt/data/mat_b.csv > result.csv

expected.csv: /opt/data/mat_c.csv
	cp /opt/data/mat_c.csv expected.csv
	dos2unix expected.csv	

test: result.csv expected.csv
	diff expected.csv result.csv
	@echo "PASS"

clean:
	rm -f *.o matmpy result.csv expected.csv
