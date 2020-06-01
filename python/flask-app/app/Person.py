class PersonData:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def get_age_in_five_years(self):
        return 'age will be '+ str(self.age + 5)
