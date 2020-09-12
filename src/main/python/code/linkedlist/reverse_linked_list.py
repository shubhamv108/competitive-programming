class ListNode:
	def __init__(self, x):
		self.val = x
		self.next = None

class Solution:
	# @param A : head node of linked list
	# @return the head node in the linked list
	def reverseList(self, A):
		prev = None
		temp = None
		while A != None:
			temp = A.next
			A.next = prev
			prev = A
			A = temp
		return prev
