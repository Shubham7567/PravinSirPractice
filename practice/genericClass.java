class Shape <E>
{
    private E element[] = (E[])new Object[10] ;
    private int index=0;

    public void setElement(E ele)
    {
        this.element[index] = ele;
        index++;
    }

    public <E> void getElement(int index)
    {
        System.out.println(this.element[index]);
    }
}

class genericClass
{
    public static void main(String[] args)
    {
        Shape<Integer> shape = new Shape<Integer>();
        int index=0;
        for(index = 0;index<5;index++)
        {
            shape.setElement(index+1);
        }
        for(index = 0;index<5;index++)
        {
            shape.getElement(index);
        }
    }
}