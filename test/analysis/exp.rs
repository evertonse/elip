fn main() {
  let exp = --1 + if false { 2 } else { if true { 2 } else {3} } * if true {2}; 
  println!("{exp}");
}