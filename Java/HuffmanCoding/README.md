#Data Structures Project

Eddie Shim 4/22/2015

In this repository, I create a Huffman tree in order to compress a text file. This method translates text into a string of 0s and 1s within a binary tree. Letters which appear more frequently in the text will have their encryptions will be located in a more optimal branch on the tree such that they have shorter translated encrypted bits. This allows for compression of text.

After encoding a string of text into Huffman code, my code additionally can decode a given Huffman code back into its orignal string text.

The file output.txt is an example of Huffman encryption and decryption printed out.

Additional description of Huffman coding can be found here: https://en.wikipedia.org/wiki/Huffman_coding

Main function is in HuffmanConverter. Run on configuration input message.txt.
