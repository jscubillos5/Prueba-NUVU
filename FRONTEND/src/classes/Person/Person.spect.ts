import { Person } from 'src/classes/Person/Person';

describe('Person', () => {
    it('should create an instance', () => {
        expect(new Person()).toBeTruthy();
    });
});
